package com.zfr.aaron.kafka.provide;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.zfr.aaron.kafka.beans.Message;
import com.zfr.aaron.kafka.consumer.KafkaReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;


/**
 * 生成者
 * @author 繁荣Aaron
 */
@Component
public class KafkaSender {

    private final Logger log = LoggerFactory.getLogger(KafkaSender.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    /**
     * 发送消息方法
     */
    public void send() {
        Message message = new Message();
        message.setId(String.valueOf(System.currentTimeMillis()));
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        kafkaTemplate.send("migrate",gson.toJson(message));
        log.info("发送成功  message = {}", gson.toJson(message));
    }
}
