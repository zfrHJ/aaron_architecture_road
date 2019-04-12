package com.zfr.aaron.kafka.beans;

import java.util.Date;

/**
 * 消息体
 * @author 繁荣Aaron
 */
public class Message {
    private String id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
