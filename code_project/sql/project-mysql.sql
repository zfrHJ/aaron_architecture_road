
-- 一、事务相关sql语句

create table T(c int) engine=InnoDB;
insert into T(c) values(1);

-- 查看默认事务
select @@tx_isolation

-- 设置事务，并查看当前的事务值
-- level 后面接隔离级别：read uncommitted / read committed / repeatable read  / serializable
set session transaction isolation level repeatable read

--查看更改后的事务
show variables like 'transaction_isolation';

-- 查看事务超过60s的事务
select * from information_schema.innodb_trx where TIME_TO_SEC(timediff(now(),trx_started))>60


--查询mysql安装地址
SELECT @@basedir;

--查询mysql数据文件地址
SELECT @@datadir;

--以windows为例
-- 查询是否开启了bin-log日志
show binary logs;

--找到 my.ini 文件
-- Binary Logging.
-- log-bin
--日志地址和名称
log-bin = mysql-bin
binlog-format =Row

--重启mysql服务-以管理员的身份
--停止服务
net stop mysql(服务名)

-- 重启服务
net start mysql(服务名)

--再次查看是否开启bin-log日志
show binary logs;

--查看bin-log的内容
show binlog events in 'mysql-bin.000001'

--flush刷新log日志，自此刻开始产生一个新编号的binlog日志文件
flush logs; 

-- 查看有多少个日志文件
show master logs; 



--canal过程

CREATE USER canal IDENTIFIED BY 'canal';  
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
-- GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' ;
FLUSH PRIVILEGES;















