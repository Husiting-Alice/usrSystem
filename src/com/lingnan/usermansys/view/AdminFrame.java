package com.lingnan.usermansys.view;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.lingnan.usermansys.comm.util.TypeUtils;
import com.lingnan.usermansys.controller.UserController;
import com.lingnan.usermansys.usermgr.domain.UserVO;
/**
 * 用户在IndexFrame中登录之后，进行权限判断，如果是管理员，则调用管理员LoginSuccess方法
 * 提示用户权限信息，并进行后续操作
 * @author 123
 *
 */
public class AdminFrame extends NormalFrame{
	/**
	 * 带参构造器，用于初始化user属性
	 */
	public AdminFrame(UserVO uVo) {
		super(uVo);
		
	}
	/**
	 * 无参构造器，实例化的时候会用到
	 */
	public AdminFrame() {
		
	}
	/**
	 * 登录成功后出现的界面，提示用户权限信息，并显示用户可进行的操作
	 */
	public void LoginSuccess() {
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
//		Scanner scanner=new Scanner(System.in);
		System.out.println("欢迎使用本系统"+uVo.getName());
		System.out.println("您的身份是：管理员");
		
		while(true) {
			System.out.println("请选择您需要的操作（请输入对应的数字）:");
			System.out.println("------查询所有用户信息----1--");
			System.out.println("------修改指定用户信息----2--");
			System.out.println("------删除指定用户信息----3--");
			System.out.println("------返回------------4--");
			int i=-1;
			while (true) {
				try {
					i=Integer.parseInt(bReader.readLine());
					break;
				}catch (Exception e) {
					System.out.println("AdminFrame 您输入的数据有误，请输入1-4中的一个数字！"+e.getMessage());
					
				}
				
			}
//			scanner.close();
			switch (i) {
			case 1:
				this.queryShow();				
				break;
            case 2:
            	this.updateShow();
				break;
            case 3:
            	this.deleteShow();
            	break;
            case 4:
            	System.out.println("再见，亲爱的管理员！");
            	return;

			default:
				System.out.println("请输入正确的选项数字!");
				break;
			}
			
			
			
		}
		
		
	}
	
	/**
	 * 查询用户信息，让用户选择查询方式
	 */
	public void queryShow(){
		System.out.println("您现在的位置是：查询所有用户信息");
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("请选择您需要的查询方式：");
			System.out.println("----按照用户名查找-----1-");
			System.out.println("----模糊查询---------2-");
			System.out.println("----分页找所有用户-----3-");
			System.out.println("----返回------------4-");
			int j=-1;
			while(true) {
				try {
				j=Integer.parseInt(bReader.readLine());
				break;
				}catch (Exception e) {
					System.out.println("AdminFrame 查询所有用户 请输入正确的数字！"+e.getMessage());
				}
			}
//			scanner.close();
			UserController uController=new UserController();
			switch (j) {			
			case 1:
				System.out.println("请输入您要查找的用户名：");
				try {
				String uname=bReader.readLine();
				uController.doQueryByUserName(uname);
				break;
				}catch (Exception e) {
					System.out.println("AdminFrame 按照用户名查找出错---"+e.getMessage());
				}
            case 2:
				System.out.println("请输入您要查找的关键字：");
				try {
				String keyWords=bReader.readLine();
				uController.doFuzzyQuery(keyWords);					
				break;
				}catch (Exception e) {
					System.out.println("AdminFrame 按照用模糊查找出错---"+e.getMessage());
				}
            case 3:
            	try {
				System.out.println("请输入页数：");
//				String keyWord=scanner.nextLine();
				int page=Integer.parseInt(bReader.readLine());
				System.out.println("请输入每页要显示的行数：");
				int row=Integer.parseInt(bReader.readLine());
				uController.findAllInfo(page, row);				
				break;
            	}
            	catch (Exception e) {
            		System.out.println("AdminFrame 分页查找出错---"+e.getMessage());
				}
            case 4:
				System.out.println("您已返回上一级");
				return;
			default:
				System.out.println("请输入正确的选项数字!");
				break;			
			}
//			scanner.close();
		}

		
	}
	/**
	 * 更新用户信息
	 */
	public void updateShow() {
		System.out.println("您现在的位置是：更新指定用户的信息");
//		Scanner scanner=new Scanner(System.in);
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
			while (true) {
			System.out.println("请输入您要修改的用户的 用户名：");
			try {
				String username=bReader.readLine();
				System.out.println("请输入要改成的新密码：");
				String passwd=bReader.readLine();
				System.out.println("请输入要修改的姓名：");
				String name=bReader.readLine();
				System.out.println("请输入要修改的性别：");
				String sex=bReader.readLine();
				System.out.println("请输入要修改的生日：");
				String birthday=bReader.readLine();
				System.out.println("请输入要修改的邮箱地址：");
				String email=bReader.readLine();
				UserVO users=new UserVO();
				users.setUsername(username);
				users.setPasswd(passwd);
				users.setName(name);
				users.setSex(sex);
				users.setBirthday(TypeUtils.strToDate(birthday));
				users.setEmail(email);
				UserController uc=new UserController();
				if(uc.UpdateUserByUsrName(users, username)) {
					System.out.println("更新成功");
				}
				else {
					System.out.println("更新失败");
				}
				System.out.println("是否继续进行更新操作？ 是y,否n");
				String yn=bReader.readLine();
				if(yn.charAt(0)=='n'||yn.charAt(0)=='N') {
					break;
					
				}				
				
				
				
			} catch (Exception e) {
				System.out.println("更新用户出错 ADMINFRAME");
				e.printStackTrace();
			}
			
			}
			
//			try {
//			String username=bReader.readLine();			
//			UserController uController=new UserController();
//			uVo=uController.doQueryByUserName(username);
//			System.out.println("请选择您需要的修改的字段：");
//			System.out.println("----修改密码..........1");
//			System.out.println("----修改姓名..........2");
//			System.out.println("----修改性别..........3");
//			System.out.println("----修改出生日期........4");
//			System.out.println("----修改邮箱地址........5");
//			System.out.println("----返回.............6");
//			
//			int j=-1;
//			while(true) {
//				try {
//				j=Integer.parseInt(bReader.readLine());
//				break;
//				}catch (Exception e) {
//					System.out.println("AdminFrame 修改指定用户 请输入正确的数字！"+e.getMessage());
//					e.printStackTrace();
//				}
//			}
////			scanner.close();
//			
//			switch (j) {			
//			case 1:
//				System.out.println("请输入您要设置的新密码：");
//				String n_passwd=bReader.readLine();
////				uVo.setPasswd(n_passwd);
//				uController.UpdateUserByUsrName(uVo, n_passwd);	
//				break;
//            case 2:
//				System.out.println("请输入您要修改的姓名：");
//				String n_name=bReader.readLine();
//				uVo.setName(n_name);
//				uController.UpdateUserByUsrName(uVo,n_name);
//				break;
//            case 3:
//            	System.out.println("请输入您要修改的性别：");
//				String n_sex=bReader.readLine();
//				uVo.setName(n_sex);
//				uController.UpdateUserByUsrName(uVo,n_sex);           	
//				break;
//            case 4:
//            	System.out.println("请输入您要修改的出生日期：");
//				String n_birthday=bReader.readLine();
//				uVo.setName(n_birthday);
//				uController.UpdateUserByUsrName(uVo,n_birthday);            	
//				break;
//            case 5:
//            	System.out.println("请输入您要修改的邮箱地址：");
//				String n_email=bReader.readLine();
//				uVo.setName(n_email);
//				uController.UpdateUserByUsrName(uVo,n_email);           	
//				break;
//            case 6:
//				System.out.println("您已返回上一级");
//				return;
//			default:
//				System.out.println("请输入正确的选项数字!");
//				break;			
//			}
//			}catch (Exception e) {
//				System.out.println("AdminFrame修改的用户出错111"+e.getMessage());
//				e.printStackTrace();
//			}
////			scanner.close();
//		}
		
	}
	/**
	 * 删除用户信息，并在删除之后让用户选择是否进行删除操作
	 */
	public void deleteShow() {
		System.out.println("您现在的位置是：删除指定用户的信息");
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
		
			System.out.println("请输入您要删除的用户的 用户名：");
			String username=bReader.readLine();
			System.out.println("您确定要删除"+username+"用户 的用户信息吗(是输入 y/Y，否输入n/N)");
			String yn=bReader.readLine();
			if(yn.charAt(0)=='y'||yn.charAt(0)=='Y') {
//			System.out.println("yyyyyyyyyyyyy");
			UserController uController=new UserController();
			uController.doDelete(username);
			}
			else if(yn.charAt(0)=='n'||yn.charAt(0)=='N') {
//				System.out.println("nnnnnnnnnnn");
				break;
			}
			else {
				System.out.println("请输入正确的字母！y或n:");
				continue;
			}
				
			System.out.println("是否继续删除？ 是y,否n ");
			String con=bReader.readLine();
			if(con.charAt(0)=='n'||con.charAt(0)=='N')
			{
				return;
			}
			else {
				continue;
			}
			}
		catch (Exception e) {
			System.out.println("AdminFrame 删除指定用户时出错"+e.getMessage());
			e.printStackTrace();
		}
//		scanner.close();		
		
		
	}

	}
}
