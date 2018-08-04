package com.lingnan.usermansys.business.service;
import java.sql.Connection;
import com.lingnan.usermansys.business.dao.UsersDao;
import com.lingnan.usermansys.comm.constant.EnumType;
import com.lingnan.usermansys.comm.dao.DaoFactory;
import com.lingnan.usermansys.comm.util.DbUtils;
import com.lingnan.usermansys.usermgr.domain.UserVO;
public class UserServiceImple implements UserService {	
	/**
	 *  用户service类实例，在类的内部创建 
	 */
	 
	private static UserService userService=new UserServiceImple();
	/**
	 * 构造方法私有化 
	 */
	 
	private UserServiceImple(){
		
	}
	/**
	 * 获取用户service实例，提供对外的访问方法，@return 实例对象
	 * 
	 */
	public static UserService getInstance() {
		return userService;
		
	}

	/**
	 * 用户登录，在本层获取并关闭数据库连接，从DAO层对象工厂获取对象，调用DAO层的登录方法
	 */
	 

	public UserVO login(String username, String password) {
	
		Connection connection = null;
		UsersDao dao = null;
//		UserServiceImple uImple =new UserServiceImple();
		UserVO user = null;
		try {
			connection = DbUtils.getConnection();
			dao = (UsersDao)DaoFactory.getDao(connection, EnumType.USERS_DAO);
			user=dao.login(username, password); 
		} catch (Exception e) {
			System.out.println("userserviceimpl 登录时出错"+e.getMessage());
			
		}finally {
			DbUtils.closeConnection(connection);
			
			
		}
		return user;
	}

	
	/**
	 * 用户注册，在本层获取并关闭数据库连接，从DAO层对象工厂获取对象，调用DAO层的登录方法
	 */
	public boolean regist(UserVO usr) {
		// 用户注册
		boolean flag=false;
		Connection connection = null;
		UsersDao dao = null;
		try {
			connection=DbUtils.getConnection();
			dao = (UsersDao)DaoFactory.getDao(connection, EnumType.USERS_DAO);
			DbUtils.beginTransaction(connection);
			flag=dao.regist(usr);
			DbUtils.commit(connection);
			
		}catch (Exception e) {
			System.out.println("userserviceimpl 注册时出错"+e.getMessage());
			DbUtils.rollback(connection);
	
		}finally {
			DbUtils.closeConnection(connection);
		}
		
		return flag;
	}

	/**
	 * 删除指定用户，在本层获取并关闭数据库连接，从DAO层对象工厂获取对象，调用DAO层的登录方法
	 */
	public boolean deleteUser(String username) {
		// 按照用户名删除指定用户
		boolean flag=false;
		Connection connection = null;
		UsersDao dao = null;
		try {
			connection =DbUtils.getConnection();
			dao=(UsersDao)DaoFactory.getDao(connection, EnumType.USERS_DAO);
			DbUtils.beginTransaction(connection);
			flag=dao.deleteUser(username);
			DbUtils.commit(connection);
			
		}catch (Exception e) {
			System.out.println("UserserviceImple 删除指定用户时出错"+e.getMessage());
			DbUtils.rollback(connection);
		}
		
		return flag;
	}

	
	/**
	 * 更新指定用户信息，在本层获取并关闭数据库连接，从DAO层对象工厂获取对象，调用DAO层的登录方法
	 */

	public boolean updateUser(UserVO usr, String username) {
		boolean flag=false;
		Connection conn = null;
		UsersDao dao = null;
		try {
			conn=DbUtils.getConnection();
			dao=(UsersDao)DaoFactory.getDao(conn, EnumType.USERS_DAO);
			DbUtils.beginTransaction(conn);
			flag=dao.updateUser(usr, username);
			DbUtils.commit(conn);
			
		}catch (Exception e) {
			System.out.println("UserserviceImple 更新指定用户时出错"+e.getMessage());
			DbUtils.rollback(conn);
			
		}finally {
			DbUtils.closeConnection(conn);
		}
		
		return flag;
	}

	/**
	 * 分页查询所有用户，在本层获取并关闭数据库连接，从DAO层对象工厂获取对象，调用DAO层的登录方法
	 */
	public UserVO findAllUser(int page, int row) {
		Connection conn = null;
		UsersDao dao = null;
		UserVO uVo=null;
		try {
			conn=DbUtils.getConnection();
			dao=(UsersDao)DaoFactory.getDao(conn, EnumType.USERS_DAO);
//			System.out.println("???111");
			dao.findAllUser(page, row);	
			
			
		}catch (Exception e) {
			System.out.println("userservice 分页查询出错"+e.getMessage());
			e.printStackTrace();
			
		}finally {
			DbUtils.closeConnection(conn);
		}
		return uVo;
	}

	/**
	 * 查询所有用户信息，在本层获取并关闭数据库连接，从DAO层对象工厂获取对象，调用DAO层的登录方法
	 */
	public UserVO findOneUser(String username) {
		Connection conn = null;
		UsersDao dao = null;
		UserVO uVo=null;
		try {
			conn=DbUtils.getConnection();
			dao=(UsersDao)DaoFactory.getDao(conn, EnumType.USERS_DAO);
			dao.findOneUser(username);	
			
		}catch (Exception e) {
			System.out.println("userservice 查询指定用户出错"+e.getMessage());
			
		}finally {
			DbUtils.closeConnection(conn);
		}
		
		return uVo;
	}
	/**
	 * 模糊查询所有用户信息，在本层获取并关闭数据库连接，从DAO层对象工厂获取对象，调用DAO层的登录方法
	 */
	public UserVO fuzzyQuery(String keyword) {
		Connection conn = null;
		UsersDao dao = null;
		UserVO uVo=null;
		try {
			conn=DbUtils.getConnection();
			dao=(UsersDao)DaoFactory.getDao(conn, EnumType.USERS_DAO);
			dao.fuzzyQuery(keyword);
			
		}catch (Exception e) {
			System.out.println("userservice 模糊查询出错"+e.getMessage());
			
		}finally {
			DbUtils.closeConnection(conn);
		}
		return uVo;
	}

}
