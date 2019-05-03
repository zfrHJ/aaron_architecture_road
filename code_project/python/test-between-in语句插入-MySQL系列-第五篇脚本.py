#0.导入数据库模板
import mysql.connector
 
#1.配置连接数据库信息
mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  passwd="123456",
  db="test_python",
  charset="utf8"
)
#2.执行方法-固定句式
mycursor = mydb.cursor()

# 如果数据表已经存在使用 execute() 方法删除表。


# 创建数据表SQL语句
sql = """CREATE TABLE userinfo (
		 id INT unsigned NOT NULL AUTO_INCREMENT,
         name  CHAR(20) NOT NULL, 
         age INT,  
         sex CHAR(1),
         income FLOAT,
		 PRIMARY KEY (id))"""


count=0
while(count < 10000):	
	
	name1 = 'zfr' + str(count)
	sex1 = 'm'
	age1 = str(count * 10)
	income1 = str(count * 10000)
	
	sql_insert = 'INSERT INTO `test_python`.`userinfo` ( `name`, `age`, `sex`, `income`) VALUES ( "%s", "%s", "%s", "%s");'% (name1,age1,sex1,income1)
	
	#sql_insert = """'INSERT INTO userinfo ( name, age, sex, income) VALUES ( '+name1 +','+ sex1+','+age1+','+ income1 +')'"""
	
	mycursor.execute(sql_insert)
	count = count + 1
	mydb.commit()

#for循环语句测试
str_i = None


# 关闭数据库连接
mydb.close()





































