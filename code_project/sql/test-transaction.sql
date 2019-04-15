
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


















