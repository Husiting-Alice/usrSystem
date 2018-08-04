package com.lingnan.usermansys.comm.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lingnan.usermansys.comm.exception.DaoException;

public class DbUtils {
	
	/**
	 * 创建数据库连接
	 * @return 返回数据库连接对象
	 * @throws InstantiationException 抛出异常
	 * @throws IllegalAccessException
	 */
	public static Connection getConnection() throws InstantiationException, IllegalAccessException {
		Connection conn = null;
		try {
			/** 1.加载数据库驱动*/
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "hst123456");
		} 
		catch (ClassNotFoundException e) {
//			System.out.println("加载jar包失败");
			throw new DaoException("加载jar包失败",e);
		} 
		catch (SQLException e) {
//			System.out.println("failed to connect to the database");
//			e.printStackTrace();
			throw new DaoException("没有连接到数据库",e);
		}
//		System.out.println("已经连接到数据库");
		return conn;
	}

	/**
	 * 开启事务
	 * @param conn 
	 */
	public static void beginTransaction(Connection conn) {
		try {
			/**将事务的自动提交模式设为假*/
			conn.setAutoCommit(false);
		}
		catch (SQLException e) {
			/** 将异常封装成自定义异常*/
            throw new DaoException("事务开启失败",e);
//			System.out.println("事务开启失败");
		}
		
	}
	
	/**
	 * 提交事务
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			/**提交事务*/
			conn.commit();
			/**将事务的自动提交模式设为真*/
			conn.setAutoCommit(true);		
			
		} catch (Exception e) {			
//			System.out.println("事务提交失败");
            throw new DaoException("事务提交失败",e);

		}
	}
	
	/**回滚事务*/
	public static void rollback(Connection conn) {
		try {
			/**回滚事务*/
		conn.rollback();
		/**将事务的自动提交模式设为真*/
		conn.setAutoCommit(true);
		}catch (Exception e) {
//			System.out.println("事务回滚失败");
            throw new DaoException("事务回滚失败",e);

		
		}		
		
	}

	
	/**
	 * 关闭数据库声明对象
	 * @param rs 结果集对象
	 * @param stat 声明对象
	 * @param prep 预编译对象
	 */
	public static void closeStatement(ResultSet rs,Statement stat,PreparedStatement prep) {
		try {
			//如果数据库结果集对象不为空，关闭该对象
			if (rs!=null) {
				rs.close();				
			}
		} catch (Exception e) {
//			System.out.println("关闭结果集对象失败");
            throw new DaoException("关闭结果集失败",e);

		}
		try {
			//如果数据库声明对象不为空，关闭该对象
			if (stat!=null) {
				stat.close();				
			}
			
		} catch (Exception e) {
//			System.out.println("关闭声明对象失败");
            throw new DaoException("关闭声明对象失败",e);

		}
		
		try {
			//如果数据库声明对象不为空，关闭该对象
			if (prep!=null) {
				prep.close();				
			}
			
		} catch (Exception e) {
            throw new DaoException("关闭预编译声明对象失败",e);
		}
		
		
}
	
	
	
	/**
	 * 关闭数据库连接对象
	 * @param conn 数据库连接对象
	 */
	public static void closeConnection(Connection conn) {
		try {
			//如果数据库连接对象不为空，关闭该对象
			if (conn!=null) {
				conn.close();				
			}			
		} catch (Exception e) {
//			System.out.println("关闭数据库、连接失败");
            throw new DaoException("关闭数据库连接失败",e);

			
		}
	
	}

	

}
