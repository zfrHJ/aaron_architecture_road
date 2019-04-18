## 一、项目介绍
1.[code_project](https://github.com/zfrHJ/aaron_architecture_road/tree/master/code_project)是代码管理库，主要是学习过程中的项目实战。主要以Java和Python语言为主，同时包括脚本语言(sh,sql)。Java以Spring Boot框架为基础进行搭建。

2.其他的都是项目资料：
* [picture](https://github.com/zfrHJ/aaron_architecture_road/tree/master/picture)文件夹是存储项目图片的；  
* [environmental_construction](https://github.com/zfrHJ/aaron_architecture_road/tree/master/environmental_construction)文件夹表示的是环境的安装，主要是在Linux上，同时部分采用的是docker容器部署方式等。

3.项目将会从服务器，框架，源码，分布式知识点进行阐述。有不对的地方，欢迎更新指正，可以关注我的公众号直接发消息进行联系！

## 二、知识整理脑图
1.GitHub上知名脑图，很多包括架构师，Java等脑图。地址是：https://github.com/TeamStuQ/skill-map

2.下面是我整理的部分知识点结构脑图，欢迎补充：
![mind-map](https://github.com/zfrHJ/aaron_architecture_road/blob/master/picture/mind-map.png)

## 三、知识点详解
下面的目录是内容大纲，大部分是以链接的方式进行跳转，如果全部把知识点放上去可能内容太多，影响用户体验。同时部分知识点，可能会以推荐书籍的方式，比如：操作系统。项目知识实战操作代码将会放在code_project文件夹下面。

### 数据结构与算法
待更新........

### 编程语言

#### 1.Java
介绍Java的特性，同时穿插源码解读。待更新........

#### 2.Python
* [环境搭建](https://mp.weixin.qq.com/s/d2C_fnbhErABT8Mwhhk5OQ)。  
* [语法讲解](https://mp.weixin.qq.com/s/TQY_ciSeFKQxRv1Q5twqyw)。

### 设计思想和架构
这部分知识点，主讲设计模式，同时穿插在项目中对领域驱动模型的理解和最新使用到的中台思想。待更新........

### 操作系统
这部分知识点将以推荐数据的方式进行呈现。待更新........

### 网络层
重点介绍TCP协议，同时讲述框架，比如Http相关的client 和 著名的netty框架。待更新........

### 数据层
数据库将以mysql为基础进行讲解。同时也会讲nosql，比如redis等。

#### Redis 系列
* [Redis分布式锁](https://mp.weixin.qq.com/s/bxIZDcmh7XW5xDuiHQ61nw)。  
* [分布式锁的总结和历史遗留问题](https://mp.weixin.qq.com/s/Y3zQewWeTSIR04IGTZcV9w)。  
* [Redis消息队列](https://mp.weixin.qq.com/s/u_WN87XJ96LVlZEMuZF91w)。  
* [Redis位图](https://mp.weixin.qq.com/s/qVNmNQnd20rtvpkSz_Lr7A)。  
* [Redis的HyperLogLong](https://mp.weixin.qq.com/s/1YNMG13l0881rZvjaS2YXw)。  
* [Redis的布隆过滤器详解和缓存穿透方案解析](https://mp.weixin.qq.com/s/gKVWCGUnCL8HtIuM3U-_6g)。  
* [Redis地理位置GEO](https://mp.weixin.qq.com/s/Bt6amvb0X6CHrDM2ntPN8A)。  
* [Redis中的Key相关操作](https://mp.weixin.qq.com/s/3bELqu1aU-U-pYra9BoMMg)。  
* [Redis线程问题和持久化知识](https://mp.weixin.qq.com/s/ykiN5Vxsb_-IXzdS2lPaFQ)。  
* [Redis的事务，安全和过期策略](https://mp.weixin.qq.com/s/TxsuNnV7BgAqMjfFQBNpBA)。  
* [Redis的开发规范(阿里云的)](https://mp.weixin.qq.com/s/8DAErvYvNMRrLfUdhqqkJw)。  
* [Redis字典hash实现和rehash过程](https://mp.weixin.qq.com/s/mYkdZeULsyHbvx6OqdMSPg)。  
* [Redis为什么那么快？存储结构是咋样的?](https://mp.weixin.qq.com/s/WrXxtrw1S6_OZ7b9AsWZxg)。  
* [哨兵机制和集群的原理。](https://mp.weixin.qq.com/s/I2b6OO1Sg3vmymuAPUru-w)。  
* [Redis调优和面试题](https://mp.weixin.qq.com/s/oqWaZsahTYFudWDbfGPUbw)。  

### 框架(应用)
重点介绍常用框架，项目常用的。比如Spring等 。待更新........

#### Kafka系列
* [场景介绍和环境搭建](https://mp.weixin.qq.com/s/IKSPS5Ile3JnCSkvTrRxwA)。  
* [项目实战](https://mp.weixin.qq.com/s/GHx1GTlJFHpzQNqPpJ68LA)。  
* [原理概念介绍](https://mp.weixin.qq.com/s/77CgXVuCuq77vZfzrrVI2Q)。  
* [kafka优化(从配置和代码上分析)](https://mp.weixin.qq.com/s/Pp5Aq5SUdbXDosmWifLmrQ)。  
* [记一次线上调优:max.request.size](https://mp.weixin.qq.com/s/7QHdmIxo8AYZbhw0zcTVnQ)。

#### zookeeper系列
* [概念和环境介绍](https://mp.weixin.qq.com/s/wn0Evzf4hj6gEd2wV773UA)。
* [选举算法和节点介绍](https://mp.weixin.qq.com/s/MOfHU7R2-pG8eqb7YkHFGA)。
* [API使用,分布式锁和服务注册与发现](https://mp.weixin.qq.com/s/W1wM5UNUFRAJZA02RLvIvw)。

### 工具
将以项目使用的软件和工具进行分类，比如提交代码的工具，编写文档的工具，画图的工具。有些会以具体的知识点重现，部分将以链接的方式，具体的使用需要自己进行学习。

* [Git入门教程](https://mp.weixin.qq.com/s/WTtVh3ZS9bNMbfDB7ZkqUQ)。

#### Linux系列
* [网络问题和文件上传](https://mp.weixin.qq.com/s/ST7YNvbtl6KH20Y5jfk7EA)。

### 面试
* [三年半 Java 后端鹅厂面试经历](https://mp.weixin.qq.com/s/oAiXvB9kZ_bNjCaK8ZyaTA)。

### 持续集成

#### Docker系列
* [docker环境搭建和介绍](https://mp.weixin.qq.com/s/Rmh5rDTjl-LyT1pOagkm1A)。
* [DockerFile介绍](https://mp.weixin.qq.com/s/qtaXJDje6eFBf_27gVGoBw)。
* [Docker容器管理/数据卷管理/容器互联](https://mp.weixin.qq.com/s/_uJTlx5OIwC22s-Lc-V0xg)。

注意：[**docker相关实战命令地址**](https://github.com/zfrHJ/aaron_architecture_road/tree/master/environmental_construction/docker)。


### 项目实战
主要以线上问题记录和热点问题提供思路解决。基础以云(腾讯云和阿里云)和微服务。
#### 云和微服务
* [线上问题：云崩溃和服务不可用](https://mp.weixin.qq.com/s/vLYAKW-w4eKr7JgXHPbUOw)。


## 四、欢迎关注本人微信公众号，扫描二维码直接关注。

![微信图片_20190320205839](https://github.com/zfrHJ/aaron_architecture_road/blob/master/picture/aaron.jpg)

