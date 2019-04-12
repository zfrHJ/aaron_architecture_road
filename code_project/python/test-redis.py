#!/usr/bin/env python3
# -*- coding: utf-8 -*-
' a test redis module '
__author__ = '繁荣Aaron'

import redis
import time


#redis 连接
pool = redis.ConnectionPool(host='localhost', port=6379)
r = redis.Redis(connection_pool=pool)

#消息队列-
def input_mq():
    #if 条件语句
    if r :
        count = 0
        while( count < 3):
            r.rpush("notify:queue","apple" + str(count))
            count = count + 1
        print(r.llen("notify:queue"))

#消息队列-延迟队列
def out_mq():
    #if 条件语句
    if r :
        count = 0;
        while( count < 3):
            print(r.brpop("notify:queue"))
            count = count + 1
        print(count)

#HyperLogLog
def her_log():
    for i in range(100000):
        r.pfadd("codehole", "user%d" % i)
    print (100000, r.pfcount("codehole"))

#位图
def set_bit():
    r.setbit("test:set:bit",1,1)
    r.setbit("test:set:bit",2,1)
    r.setbit("test:set:bit",3,0)

    #求和应该是 2 统计人数
    print(r.bitcount("test:set:bit"))

    #统计连续登入的人数
    r.setbit("test:set:bit:mun",1,1)
    r.setbit("test:set:bit:sun",3,1)
    r.setbit("test:set:bit:sta",1,1)
    r.setbit("test:set:bit:sun",1,1)
    r.setbit("test:set:bit:mun",3,1)
    r.setbit("test:set:bit:sta",3,1)
    r.bitop("and","result","test:set:bit:sta","test:set:bit:mun","test:set:bit:sun")
    print(r.bitcount("result"))


    r.set("myKey","\xff\xf0\x00")
    print(r.bitpos("myKey",0))

#布隆过滤器
def boolean_test():
    r.delete("boolean:aaron:test")
    #指定错误率
    r.execute_command("bf.reserve", "boolean:aaron:test", 0.001, 500000)
    #正式操作的命令 add 和 exists
    for i in range(10000):
        r.execute_command("bf.add", "boolean:aaron:test", "user%d" % i)
        ret = r.execute_command("bf.exists", "boolean:aaron:test", "user%d" % i)
        if ret == 0:
            print(i)
            break
        print(ret)

#geo 地理位置
def geo_hash():
    #添加
    r.geoadd("aaron:company", 114.07, 22.62, "sz", 113.23, 23.16, "gz", 113.11, 23.05, "fs")
    #距离
    print(r.geodist("aaron:company","sz","gz","km"))
    #获取元素位置
    print(r.geopos("aaron:company","sz"))

#scan 扫描方法
def scan():
    print(r.scan(0,"test*",10))


#
def key_for(user_id):
    return "account_{}".format(user_id)
#事务
def double_account(client, user_id):
    key = key_for(user_id)
    while True:
        client.watch(key)
        value = int(client.get(key))
        value *= 2  # 加倍
        pipe = client.pipeline(transaction=True)
        pipe.multi()
        pipe.set(key, value)
        try:
            pipe.execute()
            break  # 总算成功了
        except redis.WatchError:
            continue  # 事务被打断了，重试
    return int(client.get(key))  # 重新获取余额

#PubSub
def pub():
    p = r.pubsub()
    p.subscribe("codehole")
    time.sleep(1)

    print(p.get_message())
    r.publish("codehole", "java comes")
    time.sleep(1)

    print(p.get_message())
    r.publish("codehole", "python comes")
    time.sleep(1)

    print(p.get_message())
    print(p.get_message())


#PubSub 消费者 阻塞
def pub_listen():
    p = r.pubsub()
    p.subscribe("codehole")
    for msg in p.listen():
        print(msg)

#info 指令
def info():
    print(r.info("replication"))


#主函数，执行行数
if __name__ == '__main__':
    info()
    # pub()
    # user_id = "abc"
    # r.setnx(key_for(user_id), 5)  # setnx 做初始化
    # print(double_account(r, user_id))
    # scan()
    # boolean_test()
    # input_mq()
    # out_mq()
    # her_log()
    # set_bit()
    # geo_hash()
