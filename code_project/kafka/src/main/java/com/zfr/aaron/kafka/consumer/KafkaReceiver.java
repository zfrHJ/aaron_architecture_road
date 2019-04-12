package com.zfr.aaron.kafka.consumer;

import org.apache.commons.logging.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * 消费者
 * @author 繁荣Aaron
 */
@Component
public class KafkaReceiver {

    private final Logger log = LoggerFactory.getLogger(KafkaReceiver.class);

    /**
     * 案例
     * @param records
     */
    @KafkaListener(topics = {"migrate"})
    public void listen(List<ConsumerRecord> records, Acknowledgment ack) {
        try {
            for (ConsumerRecord record : records) {
                Optional<?> kafkaMessage = Optional.ofNullable(record.value());
                if (kafkaMessage.isPresent()) {
                    Object message = kafkaMessage.get();
                    log.info("----------------- record =" + record);
                    log.info("------------------ message =" + message);
                }
            }
        } catch (Exception e) {
            log.error("kafka失败，当前失败的批次。data:{}", (Throwable) records);
            e.printStackTrace();
        } finally {
            ack.acknowledge();
        }

    }

}
