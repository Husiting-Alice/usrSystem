package com.lingnan.usermansys.business.service;
import java.sql.Connection;
import com.lingnan.usermansys.business.dao.UsersDao;
import com.lingnan.usermansys.comm.constant.EnumType;
import com.lingnan.usermansys.comm.dao.DaoFactory;
import com.lingnan.usermansys.comm.util.DbUtils;
import com.lingnan.usermansys.usermgr.domain.UserVO;
public class DaoServiceImple implements DaoService {
	
	@Override
	public UserVO login(String username, String password) {
		Connection connection = null;
		UsersDao dao = null;
		UserVO user = null;
		try {
			connection = DbUtils.getConnection();
			dao = (UsersDao)DaoFactory.getDao(connection, EnumType.USERS_DAO);
			dao.login(username, password);
		} catch (Exception e) {
			
		}finally {
			
			
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
