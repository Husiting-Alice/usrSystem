package com.lingnan.usermansys.business.service;
import java.sql.Connection;
import com.lingnan.usermansys.business.dao.UsersDao;
import com.lingnan.usermansys.comm.constant.EnumType;
import com.lingnan.usermansys.comm.dao.DaoFactory;
import com.lingnan.usermansys.comm.util.DbUtils;
import com.lingnan.usermansys.usermgr.domain.UserVO;
public class UserServiceImple implements UserService {
	/*
	 * 用户service类实例，在类的内部创建 
	 */
	private static UserService userService=new UserServiceImple();
	/*
	 * 构造方法私有化 
	 */
	private UserServiceImple(){
		
	}
	/*
	 * 获取用户service实例，提供对外的访问方法，@return 实例对象
	 * 
	 */
	public static UserService getInstance() {
		return userService;
		
	}

	/*
	 * 用户登录，在本层获取并关闭数据库连接，从DAO层对象工厂获取对象，调用DAO层的登录方法
	 */
	@Override
	public UserVO login(String username, String password) {
	
		Connection connection = null;
		UsersDao dao = null;
//		UserServiceImple uImple =new UserServiceImple();
//		UserVO user = null;
		try {
			connection = DbUtils.getConnection();
			dao = (UsersDao)DaoFactory.getDao(connection, EnumType.USERS_DAO);
			dao.login(username, password);
//			uImple.login(username, password);
		} catch (Exception e) {
			
		}finally {
			DbUtils.closeConnection(connection);
			
			
		}
		return null;
	}

	@Override
	public boolean regist(UserVO usr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserVO findAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
