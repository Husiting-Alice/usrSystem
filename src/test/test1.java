package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

import com.lingnan.usermansys.business.dao.UserDaoImpl;
import com.lingnan.usermansys.comm.util.DbUtils;
import com.lingnan.usermansys.comm.util.Md5Utils;
import com.lingnan.usermansys.comm.util.TextUtils;
import com.lingnan.usermansys.comm.util.TypeUtils;
import com.lingnan.usermansys.usermgr.domain.UserVO;

class test1 {
/**
 * 单元测试，为实现测试先行的
 * @throws SQLException
 */
	@Test  //注解
	void test() throws SQLException{
		/*		
		Connection  conn =null;
		Statement stat=null;
		ResultSet rs=null;
		try {
		conn = DbUtils.getConnection();
		System.out.println(conn);	
		stat=conn.createStatement();
		System.out.println("111111");
//		String sql="update users set sex='男' where username='555555'";		
//		stat.executeUpdate(sql);	
		String sql="select Birthday from users";
		rs=stat.executeQuery(sql);
		System.out.println("222222222");
		while(rs.next()) {
			System.out.println("---"+TypeUtils.dateToStr(rs.getDate("birthday")));			
		}
//		DbUtils.commit(conn);
		DbUtils.closeStatement(rs, stat);
		DbUtils.closeConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();			
//			System.out.println("oops!faile");
			DbUtils.rollback(conn);
		}
	
	
//		System.out.println("请输入一个日期格式的字符串：");
//		Scanner scanner=new Scanner(System.in);
//		String sc=scanner.next();		
//		System.out.println(TypeUtils.strToDate(sc));
//		scanner.close();
		
	*/
		
//		System.out.println("请输入一个邮箱地址：");
//		Scanner scanner=new Scanner(System.in);
//		String sc=scanner.next();
//		if(TypeUtils.checkEmail(sc))
//			System.out.println("是正确的邮箱地址");
//		else
//			System.out.println("错误的邮箱地址");
		
		//？？？
//		System.out.println("请输入一个字符串：");
//		Scanner scanner=new Scanner(System.in);
//		String sc=scanner.next();
//		if(TextUtils.isEmpty(sc))
//			System.out.println("是空串");
//		else
//			System.out.println("不是空串");
		
		
//		String str="hst123456";	
//		String aamd5=Md5Utils.md5Jdk(str);
//		System.out.println(aamd5);
//		System.out.println(aamd5.length());
//		System.out.println(str.length());
		Connection conn=null;
		try {
		conn=DbUtils.getConnection();
		UserDaoImpl ui=new UserDaoImpl(conn);
//        ui.login("111111", "111111");
		
//		ui.findAllUser();
		
//		if(ui.deleteUser("111111"))
//			System.out.println("成功删除用户!");
		
		UserVO usr=new UserVO();
		usr.setUsername("666666");
		usr.setPasswd("666666");
		usr.setPower(1);
		usr.setName("黄蔡糀");
		usr.setSex("女");
		usr.setBirthday(TypeUtils.strToDate("1909-09-09"));
		
		
//		usr.setBirthday(Date("1990-09-09"));
//		usr.setBirthday("1909-09-09");
		usr.setEmail("666666@139.com");
		usr.setStatus(1);		
		ui.regist(usr);
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)
				conn.close();
		}
		
	
	}
}

