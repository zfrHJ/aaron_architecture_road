
-- 一、获取时间
--1.获取当前日期+时间
now();
sysdate();

--这两个函数都是获取日期+时间，不同之处在于：now()在执行开始时值就得到了，sysdate()在函数执行时动态得到值

--2.获取当前日期：
curdate();
current_date();
current_date;
--3.获取当前时间：
curtime();
current_time();
current_time;

--4.获取UTC时间
--A.获取UTC日期：
utc_date();
--B.获取UTC时间：
utc_time();
--C.获取UTC日期+时间：
utc_timestamp;

--获取前一天时间
select date_sub(curdate(),interval 1 day)  from DUAL;


-- 二、性能查看命令
--导出数据库
mysqldump -u dev_erp -p erp2_cust_traced > erp2_cust_traced.sql


-- 1.查看mysql版本号：
select version();

-- 2.查看mysql存储引擎
show engines;

-- 3.查看mysql的缓冲池大小配置，注意：缓冲池大小为：8388608B/1024/1024=8M，一般设置为机器的物理内存的80%
show variables like 'innodb_buffer_pool_size' ;


show variables like 'innodb_io%';

-- 5.慢查询 slow_query_log = on 打开了慢查询日志，执行时间超过2秒的即为慢查询
show variables like '%slow%';

-- 6.最大连接数
show variables like 'max_connections'; 

--查询一下服务器响应的最大连接数：
show global status like 'Max_used_connections';

-- 7.进程使用情况
show global status like 'Thread%'; 

--Threads_created表示创建过的线程数，如果发现Threads_created值过大的话，表明MySQL服务器一直在创建线程，这也是比较耗资源，可以适当增加配置文件中thread_cache_size值，查询服务器thread_cache_size配置：
show variables like 'thread_cache_size'; 

-- 8.查询缓存
show global status like 'qcache%'; 

--MySQL查询缓存变量解释：
--Qcache_free_blocks：缓存中相邻内存块的个数。数目大说明可能有碎片。FLUSH QUERY CACHE会对缓存中的碎片进行整理，从而得到一个空闲块。
--Qcache_free_memory：缓存中的空闲内存。 
--Qcache_hits：每次查询在缓存中命中时就增大
--Qcache_inserts：每次插入一个查询时就增大。命中次数除以插入次数就是不中比率。
--Qcache_lowmem_prunes：缓存出现内存不足并且必须要进行清理以便为更多查询提供空间的次数。这个数字最好长时间来看;如果这个数字在不断增长，就表示可能碎片非常严重，或者内存很少。(上面的 free_blocks和free_memory可以告诉您属于哪种情况) 
--Qcache_not_cached：不适合进行缓存的查询的数量，通常是由于这些查询不是 SELECT 语句或者用了now()之类的函数。
--Qcache_queries_in_cache：当前缓存的查询(和响应)的数量。
--Qcache_total_blocks：缓存中块的数量。

--再查询一下服务器关于query_cache的配置
show variables like 'query_cache%'; 

--query_cache_limit：超过此大小的查询将不缓存
--query_cache_min_res_unit：缓存块的最小大小
--query_cache_size：查询缓存大小
--query_cache_type：缓存类型，决定缓存什么样的查询，示例中表示不缓存 select sql_no_cache 查询 
--query_cache_wlock_invalidate：当有其他客户端正在对MyISAM表进行写操作时，如果查询在query cache中，是否返回cache结果还是等写操作完成再读表获取结果。
--query_cache_min_res_unit的配置是一柄”双刃剑”，默认是4KB，设置值大对大数据查询有好处，但如果你的查询都是小数据查询，就容易造成内存碎片和浪费。
--查询缓存碎片率 = Qcache_free_blocks / Qcache_total_blocks * 100%
--如果查询缓存碎片率超过20%，可以用FLUSH QUERY CACHE整理缓存碎片，或者试试减小query_cache_min_res_unit，如果你的查询都是小数据量的话。
--查询缓存利用率 = (query_cache_size - Qcache_free_memory) / query_cache_size * 100%
--查询缓存利用率在25%以下的话说明query_cache_size设置的过大，可适当减小;查询缓存利用率在80%以上而且Qcache_lowmem_prunes > 50的话说明query_cache_size可能有点小，要不就是碎片太多。 
--查询缓存命中率 = (Qcache_hits - Qcache_inserts) / Qcache_hits * 100%

-- 9.排序使用情况
show global status like 'sort%'; 
 --Sort_merge_passes 包括两步。MySQL 首先会尝试在内存中做排序，使用的内存大小由系统变量 Sort_buffer_size 决定，如果它的大小不够把所有的记录都读到内存中，MySQL 就会把每次在内存中排序的结果存到临时文件中，等 MySQL 找到所有记录之后，再把临时文件中的记录做一次排序。这再次排序就会增加 Sort_merge_passes。实际上，MySQL 会用另一个临时文件来存再次排序的结果，所以通常会看到 Sort_merge_passes 增加的数值是建临时文件数的两倍。因为用到了临时文件，所以速度可能会比较慢，增加 Sort_buffer_size 会减少 Sort_merge_passes 和 创建临时文件的次数。但盲目的增加 Sort_buffer_size 并不一定能提高速度

-- 10.打开的文件数

show global status like 'open_files'; 

show variables like 'open_files_limit'; 

-- 注意：比较合适的设置：Open_files / open_files_limit * 100% <= 75%

-- 11. 表锁情况

show global status like 'table_locks%'; 

--Table_locks_immediate表示立即释放表锁数，Table_locks_waited表示需要等待的表锁数，如果Table_locks_immediate / Table_locks_waited > 5000，最好采用InnoDB引擎，因为InnoDB是行锁而MyISAM是表锁，对于高并发写入的应用InnoDB效果会好些。示例中的服务器Table_locks_immediate / Table_locks_waited = 235，MyISAM就足够了。

-- 12. 表扫描情况 
show global status like 'handler_read%'; 

show global status like 'com_select'; 

--查看行数，类似count(*)操作
show table status

--查看查询情况，是否有表锁，Waiting for table metadata lock

show processlist

