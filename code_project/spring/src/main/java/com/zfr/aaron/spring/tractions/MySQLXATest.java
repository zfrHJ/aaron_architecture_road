package com.zfr.aaron.spring.tractions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLXATest {
	
	public static void main(String[] args) throws SQLException {
		// 创建抽奖库的RM实例
		Connection lotteryConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/data-refill-center-lottery", 
				"root", 
				"root");
		/*// 这里的这个true参数，是说打印出来XA分布式事务的一些日志
		XAConnection lotteryXAConnection = new MysqlXAConnection(
				(com.mysql.jdbc.Connection)lotteryConnection, true); 
		// 这个XAResource其实你可以认为是RM（Resource Manager）的一个代码中的对象实例
		XAResource lotteryResource = lotteryXAConnection.getXAResource();
		
		// 创建积分库的RM实例
		Connection creditConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/data-refill-center-credit", 
				"root", 
				"root");
		XAConnection creditXAConnection = new MysqlXAConnection(
				(com.mysql.jdbc.Connection)creditConnection, true);
		XAResource creditResource = creditXAConnection.getXAResource();
      
		// 下面俩东西是分布式事务id（txid）的构成部分
		byte[] gtrid = "g12345".getBytes();
		int formatId = 1;
		
		try {
			// 这是说在分布式事务中的抽奖库的子事务的标识
			// 我们在抽奖库要执行的操作隶属于分布式事务的一个子事务，子事务有自己的一个标识
			byte[] bqual1 = "b00001".getBytes();
        	Xid xid1 = new MysqlXid(gtrid, bqual1, formatId); // 这个xid代表了抽奖库中的子事务
        	
        	// 这就是说通过START和END两个操作，定义好了分布式事务中，抽奖库中要执行的SQL语句
        	// 但是这里的SQL绝对不会执行的，只是说先定义好我要在分布式事务中，这个数据库里要执行哪些SQL语句
        	lotteryResource.start(xid1, XAResource.TMNOFLAGS);
        	PreparedStatement lotteryPreparedStatement = lotteryConnection.prepareStatement(
        			"UPDATE lottery_draw SET lottery_draw_count=lottery_draw_count+1 WHERE id=1");
        	lotteryPreparedStatement.execute();
        	lotteryResource.end(xid1, XAResource.TMSUCCESS);
        	
        	// 这是说在分布式事务中的积分库的子事务的标识
        	// 大家看下，积分库的子事务的xid中的，gtrid和formatId是一样的，bqual是不一样的
        	// 在一个分布式事务中，涉及到多个数据库的子事务，每个子事务的txid，有一部分是一样的，一部分是不一样的
        	byte[] bqual2 = "b00002".getBytes();
        	Xid xid2 = new MysqlXid(gtrid, bqual2, formatId);
        	// 这就是说通过START和END两个操作，定义好了分布式事务中，积分库中要执行的SQL语句
        	creditResource.start(xid2, XAResource.TMNOFLAGS);
        	PreparedStatement creditPreparedStatement = creditConnection.prepareStatement(
        			"UPDATE credit SET POINT=POINT+1.2 WHERE id=1");
        	creditPreparedStatement.execute();
        	creditResource.end(xid2, XAResource.TMSUCCESS);
        	
        	// 到这里为止，其实还啥都没干呢，不过就是定义了分布式事务中的两个库要执行的SQL语句罢了
        	
        	// 2PC的阶段一：向两个库都发送prepare消息，执行事务中的SQL语句，但是不提交
        	int lotteryPrepareResult = lotteryResource.prepare(xid1);
        	int creditPrepareResult = creditResource.prepare(xid2);
        	
        	// 2PC的阶段二：两个库都发送commit消息，提交事务
        	
        	// 如果两个库对prepare都返回ok，那么就全部commit，对每个库都发送commit消息，完成自己本地事务的提交
	        if (lotteryPrepareResult == XAResource.XA_OK
	               && creditPrepareResult == XAResource.XA_OK) {
	        	lotteryResource.commit(xid1, false);
	        	creditResource.commit(xid2, false);
	        } 
	        // 如果如果不是所有库都对prepare返回ok，那么就全部rollback
	        else {
	        	lotteryResource.rollback(xid1);
	        	creditResource.rollback(xid2);
	        }*/
		/*} catch (XAException e) {
			e.printStackTrace();
		}*/
   }
   
}