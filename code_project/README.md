## 1.项目介绍
 该code_project项目包括Kafka，python，redis，spring，sql五个文件夹，除了python和sql以脚本，其他的都是以SpringBoot框架为基础，分别以Kafka，redis为框架的知识点，其中spring项目现在只包含注解的应用，后面会继续优化该内容，比如Aop等知识点。
 ###Spring项目。
 该项目将作为重点的发展项目，比如：添加实际项目常用的工具类(字符串、时间、bean拷贝、获取Springbean)都放在project包下面。同时，相关性能测试类，将以Controller入口的方式进行，并且都已经注释了（比如：测试mybatis批量插入，那个性能高点。是mybatis-plus，还是jdbc原生批量快？）。

## 1.1 python项目
 该项目主要以Redis框架相关的命令为基础，作为项目。test-redis，test表示该文件是以测试为主的；-redis，即-XXX，表示讲述的那个知识点。即test-redis，讲述的是redis相关的python代码知识点。

## 1.2 sql文件夹
 该文件夹下主要为mysql数据库为主，该项目关联的是mysql从入门到精通的脚本文件。test-explain.sql，test表示的是测试为主的，-explain表示讲述的是哪个知识点。即test-explain.sql讲述的是explain知识点。

## 1.3 properties文件夹
 主要以配置文件为主，比如nginx等。