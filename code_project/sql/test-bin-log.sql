-- 是否启用binlog日志
show variables like 'log_bin';

-- 查看详细的日志配置信息
show global variables like '%log%';

-- mysql数据存储目录
show variables like '%dir%';

-- 查看binlog的目录
show global variables like "%log_bin%";

-- 查看当前服务器使用的biglog文件及大小
show binary logs;

-- 查看主服务器使用的biglog文件及大小

-- 查看最新一个binlog日志文件名称和Position
show master status;


-- 事件查询命令
-- IN 'log_name' ：指定要查询的binlog文件名(不指定就是第一个binlog文件)
-- FROM pos ：指定从哪个pos起始点开始查起(不指定就是从整个文件首个pos点开始算)
-- LIMIT [offset,] ：偏移量(不指定就是0)
-- row_count ：查询总条数(不指定就是所有行)
show binlog events [IN 'log_name'] [FROM pos] [LIMIT [offset,] row_count];

-- 查看 binlog 内容
show binlog events;

-- 查看具体一个binlog文件的内容 （in 后面为binlog的文件名）
show binlog events in 'master.000003';

-- 设置binlog文件保存事件，过期删除，单位天
set global expire_log_days=3; 

-- 删除当前的binlog文件
reset master; 

-- 删除slave的中继日志
reset slave;

-- 删除指定日期前的日志索引中binlog日志文件
purge master logs before '2019-03-09 14:00:00';

-- 删除指定日志文件
purge master logs to 'master.000003';

--查询mysql安装地址
SELECT @@basedir;

--查询mysql数据文件地址
SELECT @@datadir;

--以windows为例进行配置
-- 查询是否开启了bin-log日志
show binary logs;

--找到 my.ini 文件
# Binary Logging.
# log-bin
#日志地址和名称
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

-- 查看binllog文本内容这个是在Linux下的bin，执行的。window下我没有执行成功。
-- mysqlbinlog 的执行格式
mysqlbinlog [options] log_file ...

-- 查看bin-log二进制文件（shell方式）
mysqlbinlog -v --base64-output=decode-rows /var/lib/mysql/master.000003

-- 查看bin-log二进制文件（带查询条件）
mysqlbinlog -v --base64-output=decode-rows /var/lib/mysql/master.000003 \
    --start-datetime="2019-03-01 00:00:00"  \
    --stop-datetime="2019-03-10 00:00:00"   \
    --start-position="5000"    \
    --stop-position="20000"
