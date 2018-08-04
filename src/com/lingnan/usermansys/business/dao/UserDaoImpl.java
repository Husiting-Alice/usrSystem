package com.lingnan.usermansys.business.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import com.lingnan.usermansys.comm.exception.ServiceException;
import com.lingnan.usermansys.comm.util.DbUtils;
import com.lingnan.usermansys.comm.util.Md5Utils;
import com.lingnan.usermansys.usermgr.domain.UserVO;

/**
 * 实现UserDao中的方法
 */
public class UserDaoImpl implements UsersDao {

	public UserDaoImpl() {
		super();
	}
//Connection conn=null;
	private Connection conn;

	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 登录功能
	 */
	
	public UserVO login(String name, String password) {
		// 用户登录		
		ResultSet rs = null;
		PreparedStatement prep = null;
		UserVO userVO = null;
		try {
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
//				String identity=null;
//				if (userVO.getPower()==2)
//					identity="管理员";
//				else 
//					identity="普通用户";
//				System.out.println(userVO.getName()+"，欢迎登录本系统");
//				System.out.println("您的身份是："+identity);

			}

		} catch (Exception e) {
			throw new ServiceException("用户登录的时候出错了。。。", e);

		} finally {
			DbUtils.closeStatement(rs, null, prep);

		}
//		System.out.println(userVO+"121");
		return userVO;
	}
/**
 * 用户注册
 */
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
//		int status = usr.getStatus();

		try {
			prep = conn.prepareStatement("insert into users values(?,?,?,?,?,?,?,1)");
			prep.setString(1, username);
			prep.setString(2, pwd);
			prep.setInt(3, power);
			prep.setString(4, name);
			prep.setString(5, sex);
			prep.setDate(6, new java.sql.Date(birthday.getTime()));
			prep.setString(7, email);
//			prep.setInt(8, status);			
			
			prep.executeUpdate();
//			DbUtils.commit(conn);
			flag = true;
			System.out.println("注册成功");

		} catch (Exception e) {
			throw new ServiceException("用户注册的时候出错了。。。", e);

		} finally {
			DbUtils.closeStatement(null, null, prep);

		}
		return flag;
	}
/**
 * 输入用户名，删除指定用户
 */
	public boolean deleteUser(String username) {
		// 管理员删除指定用户 (软删除，要commit)
		boolean flag = false;
		PreparedStatement prep = null;
		try {
			String sql = "update users set status=0 where username='" + username + "'";
			prep = conn.prepareStatement(sql);
			prep.executeUpdate();
			flag = true;

		} catch (Exception e) {
			throw new ServiceException("userDaoImpl 删除指定用户的时候出错了。。。", e);

		} finally {
			DbUtils.closeStatement(null, null, prep);

		}
		return flag;
	}

	/**
	分页查询所有用户信息,page为要显示的第几页，row为每页有几行
	*/
	public UserVO findAllUser(int page,int row) {
			
			ResultSet rs = null;
			PreparedStatement prep = null;
			UserVO user = null;
			try {
	/**
	下面是实现分页的SQL语句
	*/
				String sql = "select * from (select a.*,rownum rn from (select * from users where status=1) a) where  "+row+"*"+page+" >= rn and ("+page+"-1)*"+row+" < rn";
	/**
	Md5Utils.md5Jdk(user.getPasswd())为MD5加密算法，对密码进行加密处理，自定义然后封装在工具类中
	*/
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
	/**
	DbUtils.closeStatement(rs, null, prep)为封装在数据库中的关闭声明对象的方法
	*/
				DbUtils.closeStatement(rs, null, prep);
	 
			}
	 
			return user;
		}

	/**
	 *  查询指定用户信息,根据用户名查找，用户名为String类型
	 */
	public UserVO findOneUser(String username) {
		
		ResultSet rs = null;
		PreparedStatement prep = null;
		UserVO user = null;
		try {
			String sql = "select * from users where username='"+username+"' and status=1";
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
	/**
	 * 模糊查询，输入关键字（String类型），对表中多个字段进行模糊查询
	 */
	public UserVO fuzzyQuery(String keyword) {
		UserVO user=null;
		ResultSet rs = null;
		PreparedStatement prep = null;
		try {
			String sql="select * from users where status=1 and (username like '%'||'"+keyword+"'||'%' or passwd like '%'||'"+keyword+"'||'%' or power like '%'||'"+keyword+"'||'%' or name like '%'||'"+keyword+"'||'%' or sex like '%'||'"+keyword+"'||'%' or birthday like '%'||'"+keyword+"'||'%' or email like '%'||'"+keyword+"'||'%')";
			prep=conn.prepareStatement(sql);
			rs=prep.executeQuery();
			while(rs.next()) {
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
				+Md5Utils.md5Jdk(user.getPasswd())+" "  /**md5Jdk(String passwd)为封装在Md5Utils中的MD5加密算法实现，自定义*/
				+user.getPower()+" "
				+user.getName()+" "
				+user.getSex()+" "
				+user.getBirthday()+" "
				+user.getEmail()+" "
				+user.getStatus()
				);
				
			}
			
		}catch (Exception e) {
			System.out.println("模糊查询---daoimpl 出错"+e.getMessage());
			e.printStackTrace();
		
		}
		return user;
		
	}
	/**更新用户信息*/
	public boolean updateUser(UserVO usr, String username) {
		
		boolean flag = false;
		PreparedStatement prep = null;	
		String pwd = usr.getPasswd();
		String name = usr.getName();
		String sex = usr.getSex();
		Date birthday = usr.getBirthday();
		String email = usr.getEmail();
		String sql="update users set passwd=?,name=?,sex=?,birthday=?,email=? where username='"+username+"' and status=1";
		try {
			prep=conn.prepareStatement(sql);			
			prep.setString(1, pwd);
			prep.setString(2, name);
			prep.setString(3, sex);
			prep.setDate(4, new java.sql.Date(birthday.getTime()));
			prep.setString(5, email);
			prep.executeUpdate();
			flag=true;			
			
		} catch (Exception e) {
			System.out.println("更新用户出错 ---DAOIMPL"+e.getMessage());
			
		}finally {
			DbUtils.closeStatement(null, null, prep);
		}
		
		
		return flag;
	}




}




