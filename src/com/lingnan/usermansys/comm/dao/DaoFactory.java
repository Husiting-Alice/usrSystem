package com.lingnan.usermansys.comm.dao;

import java.sql.Connection;

import com.lingnan.usermansys.business.dao.UserDaoImpl;
import com.lingnan.usermansys.comm.exception.ServiceException;

public class DaoFactory {
	/*
	 * 获得DAO对象的工厂方法
	 * @param conn 数据库连接对象
	 * @param type dao常量，传入对象
	 * @return DAO父接口
	 */
	public static BaseDao getDao(Connection conn,String type) {
		//如果传入的DAO类型是用户users（即users表），就去实例化用户DAO实现类
		if ("users".equals(type)) {
			//返回实例化的DAO对象
			return new UserDaoImpl(conn);
			
		}
		//如果传入的DAO类型是用户order（即order表），就去实例化订单DAO实现类
//		else if("order".equals(type)) {
//			return new OrderDaoImpl(conn);
//		}
		//如果没有和以上指定类型匹配的值，就提示错误信息
		else {
			throw new ServiceException("dao工厂方法出错");
		}		
		
		
		
	}

}
