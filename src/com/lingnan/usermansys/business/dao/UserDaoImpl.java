package com.lingnan.usermansys.business.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import com.lingnan.usermansys.comm.exception.ServiceException;
import com.lingnan.usermansys.comm.util.DbUtils;
import com.lingnan.usermansys.comm.util.Md5Utils;
import com.lingnan.usermansys.usermgr.domain.UserVO;

public class UserDaoImpl implements UsersDao {

	public UserDaoImpl() {
		super();
	}
//Connection conn=null;
	private Connection conn;

	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}

	public UserVO login(String name, String password) {
		// 用户登录		
		ResultSet rs = null;
		PreparedStatement prep = null;
		UserVO userVO = null;
		try {
//			Connection conn=DbUtils.getConnection();
			String sql = "select * from users where username=? " + "and passwd=? and status=1";
			prep = conn.prepareStatement(sql);
			prep.setString(1, name);
			prep.setString(2, password);
			rs = prep.executeQuery();
			while (rs.next()) {
				userVO = new UserVO();
				userVO.setUsername(rs.getString("username"));
				userVO.setPasswd(rs.getString("passwd"));
				userVO.setPower(rs.getInt("power"));
				userVO.setName(rs.getString("name"));
				userVO.setSex(rs.getString("sex"));
				userVO.setBirthday(rs.getDate("birthday"));
				userVO.setEmail(rs.getString("email"));
				userVO.setStatus(rs.getInt("status"));
				String identity=null;
				if (userVO.getPower()==2)
					identity="管理员";
				else 
					identity="普通用户";
				System.out.println(userVO.getName()+"，欢迎登录本系统");
				System.out.println("您的身份是："+identity);

			}

		} catch (Exception e) {
			throw new ServiceException("用户登录的时候出错了。。。", e);

		} finally {
			DbUtils.closeStatement(rs, null, prep);

		}

		return userVO;
	}

	public boolean regist(UserVO usr) {
		// 注册
		boolean flag = false;
		PreparedStatement prep = null;
		String username = usr.getUsername();
		String pwd = usr.getPasswd();
		int power = usr.getPower();
		String name = usr.getName();
		String sex = usr.getSex();
		Date birthday = usr.getBirthday();
		String email = usr.getEmail();
		int status = usr.getStatus();

		try {
			String sql = "insert into users values('" + username + "','" + pwd + "'," 
					      + power + ",'" + name + "','"+ sex + "','" + birthday + "','"
					+ email +"'," + status + ")";
			prep = conn.prepareStatement(sql);
			prep.executeUpdate();
			DbUtils.commit(conn);
			flag = true;

		} catch (Exception e) {
			throw new ServiceException("用户注册的时候出错了。。。", e);

		} finally {
			DbUtils.closeStatement(null, null, prep);

		}
		return flag;
	}

	public boolean deleteUser(String username) {
		// 管理员删除指定用户 (软删除，要commit)
		boolean flag = false;
		PreparedStatement prep = null;
		try {
			String sql = "update users set status=0 where username='" + username + "'";
			prep = conn.prepareStatement(sql);
			prep.executeUpdate();
			DbUtils.commit(conn);
			flag = true;

		} catch (Exception e) {
			throw new ServiceException("删除指定用户的时候出错了。。。", e);

		} finally {
			DbUtils.closeStatement(null, null, prep);

		}
		return flag;
	}



	public UserVO findAllUser() {
		// 查询所有用户信息
		ResultSet rs = null;
		PreparedStatement prep = null;
		UserVO user = null;
		try {
			String sql = "select * from users where status=1";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				user = new UserVO();
				user.setUsername(rs.getString("username"));
				user.setPasswd(rs.getString("passwd"));
				user.setPower(rs.getInt("power"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getString("sex"));
				user.setBirthday(rs.getDate("birthday"));
				user.setEmail(rs.getString("email"));
				user.setStatus(rs.getInt("status"));
				System.out.println(user.getUsername()+" "
				+Md5Utils.md5Jdk(user.getPasswd())+" "
				+user.getPower()+" "
				+user.getName()+" "
				+user.getSex()+" "
				+user.getBirthday()+" "
				+user.getEmail()+" "
				+user.getStatus()
						);
			}

		} catch (Exception e) {
			throw new ServiceException("查询所有用户信息的时候出错了，，。", e);

		} finally {

		}

		return user;
	}
	
	public boolean updateUser() {
		//
		return false;
	}

}
