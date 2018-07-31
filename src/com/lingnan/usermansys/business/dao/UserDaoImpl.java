package com.lingnan.usermansys.business.dao;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import com.lingnan.usermansys.comm.exception.ServiceException;
import com.lingnan.usermansys.comm.util.DbUtils;
import com.lingnan.usermansys.comm.util.Md5Utils;
import com.lingnan.usermansys.usermgr.domain.UserVO;

import oracle.sql.TIMESTAMP;

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
			prep = conn.prepareStatement("insert into users values(?,?,?,?,?,?,?,?)");
			prep.setString(1, username);
			prep.setString(2, pwd);
			prep.setInt(3, power);
			prep.setString(4, name);
			prep.setString(5, sex);
			prep.setDate(6, new java.sql.Date(birthday.getTime()));
			prep.setString(7, email);
			prep.setInt(8, status);			
			
			prep.executeUpdate();
			DbUtils.commit(conn);
			flag = true;
			System.out.println("注册成功");

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
			DbUtils.closeStatement(rs, null, prep);

		}

		return user;
	}
	
	public boolean updateUser(UserVO usr) {
		//更新用户信息,让用户指定需要更新的字段，而非全部更新
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
//			prep=conn.prepareStatement("update users")
			
			
		} catch (Exception e) {
			
		}finally {
			DbUtils.closeStatement(null, null, prep);
		}
		
		
		return false;
		/*
		 * create or replace procedure pro_updateuser
(
a_passwd in Varchar2,
a_name in varchar2,
a_sex in char,
a_birthday varchar2,
a_email varchar2,
p_cursor out sys_refcursor
)
as
str1  varchar2(200):=' select * from users' ||  
                        ' where   
                        password like :a_passwd and 
                        name like :a_name and
                        sex like :a_sex and
                        birthday like :a_birthday and
                        email like :a_email                         
                        ' ;
begin
         open p_cursor for str1 using  '%' || a_passwd|| '%' , '%'|| a_name||'%',
         '%'|| a_sex||'%','%'|| a_birthday||'%','%'|| a_email||'%';
end  pro_updateuser;

		 * 
		 * 
		 * 
		 * 
		 */
	}


}




