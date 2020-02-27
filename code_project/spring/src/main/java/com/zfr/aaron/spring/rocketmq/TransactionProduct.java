package com.zfr.aaron.spring.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  用支付后发红包的案例场景，分析RocketMQ事物消息的代码实现细节
 */
public class TransactionProduct {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {

        //=================================================事务 ==============================================================

        TransactionListenerImpl transactionListener = new TransactionListenerImpl();

        TransactionMQProducer producer = new TransactionMQProducer("TestProducerGroup");

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("TestThread");
                        return thread;
                    }
                });
        //给事务消息生产者设置对应的线程池，负责执行RocketMQ回调请求
        producer.setExecutorService(executorService);
        //给事务消息生产者设置对应的回调函数
        producer.setTransactionListener(transactionListener);
        //启动这个事务消息生产者
        producer.start();

        Message msg = new Message("PayOrder","testTag","testKey","订单支付".getBytes(RemotingHelper.DEFAULT_CHARSET));
        //将消息作为half消息的模式发送出去
        try {
            producer.sendMessageInTransaction(msg,null);
        }catch (Exception e){
            //half消息失败
            //订单系统执行回滚逻辑，比如说触发支付退款，更新订单状态为"已关闭"
        }

        //================================================穿插有序订单的写入==================================================

        producer.send(msg, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                Long aLong = (Long) o;
                long l = aLong % list.size();
                return list.get((int)l);
            }
        },"orderId");

        //消费者修改
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TestProducerGroup");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {

                consumeOrderlyContext.setAutoCommit(true);

                try {
                    for(MessageExt msg: list){
                        //对有序的消息进行处理
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }catch (Exception e){
                    //如果消息处理有问题
                    //返回一个状态，让他暂停一会儿在继续处理这批消息
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
            }
        });

        //================================================延迟队列==========================================================
        //订单生产者
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("OrderSystemProducerGroup");
        //启动生产者
        defaultMQProducer.start();
        //创建订单通知Topic
        Message message = new Message("CreateOrderInformTopic",
                "订单信息json".getBytes());

        //设置了消息为延迟消息，延迟级别为3
        message.setDelayTimeLevel(3);

        //发送消息
        producer.send(message);

        //消费者
        DefaultMQPushConsumer mqProducer = new DefaultMQPushConsumer("OrderSystemProducerGroup");

        mqProducer.subscribe("CreateOrderInformTopic","*");

        mqProducer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                for(MessageExt messageExt : list){
                    //这里打印一下消息的存储时间到消费时间的差值
                    //大概就是我们设置的延迟级别的时间

                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        mqProducer.start();

    }

    public static class TransactionListenerImpl implements TransactionListener{

        private AtomicInteger transactionIndex = new AtomicInteger(0);

        private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

        /**
         * 如果half消息发送成功了
         * 就会在这里回调你的这个函数，你就可以执行本地事务了
         * @param message
         * @param o
         * @return
         */
        @Override
        public LocalTransactionState executeLocalTransaction(Message message, Object o) {

            int value = transactionIndex.getAndIncrement();
            int status = value % 3;
            localTrans.put(message.getTransactionId(), status);
            //执行订单本地事务
            //接着根据本地一连串事务执行结果，去选择执行commit or rollback
            try {
                //如果本地事务都执行成功了，返回commit
                return LocalTransactionState.COMMIT_MESSAGE;
            }catch (Exception e){
                //本地事务执行失败，回滚所有一切执行过的操作
                //如果本地事务执行失败了，返回rollback，标记half消息无效
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }

        /**
         * 如果因为各种原因，没有返回commit或者rollback
         * @param messageExt
         * @return
         */
        @Override
        public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
            //查询本地事务，是否执行成功了
            String msgId = messageExt.getMsgId();
            Integer status = localTrans.get(msgId);
            if (null != status) {
                switch (status) {
                    case 0:
                        // 检查中
                        return LocalTransactionState.UNKNOW;
                    case 1:
                        // 提交消息
                        return LocalTransactionState.COMMIT_MESSAGE;
                    case 2:
                        // 回滚消息
                        return LocalTransactionState.ROLLBACK_MESSAGE;
                }
            }
            return LocalTransactionState.COMMIT_MESSAGE;
        }
    }
}
