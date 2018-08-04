package com.lingnan.usermansys.view;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import com.lingnan.usermansys.comm.util.TypeUtils;
import com.lingnan.usermansys.controller.UserController;
import com.lingnan.usermansys.usermgr.domain.UserVO;
/**
 * 用户还未登录时显示的界面，用户可以选择登录或注册，这两个方法都在本类中实现
 * @author 123
 *
 */
public class IndexFrame implements BaseFrame{

	/**
	 *  用户还未登录时显示的界面
	 */
	public void show() {
//		Scanner scanner=new Scanner(System.in);
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("     欢迎使用用户管理系统          ");
			System.out.println("-----请选择您需要的操作-----");
			System.out.println("--------登录系统---------1");
			System.out.println("--------新用户注册--------2");
			System.out.println("--------退出系统---------3");
			int i=-1;
			while(true) {
				try {
				i=Integer.parseInt(bReader.readLine());				
				break;
				}catch (Exception e) {
					System.out.println("从控制台接收数据时出错-index"+e.getMessage());
				    e.printStackTrace();
				}
			}
//			scanner.close();
			switch(i) {
			case 1:
//				System.err.println("123123*******");
				this.loginShow();	
				break;
			case 2:
				this.registShow();
				break;
			case 3:
				System.out.println("感谢使用本系统，再会！");
				System.exit(0);		
				//System.exit(0)是正常退出程序，而System.exit(1)或者说非0表示非正常退出程序
			default:
				System.out.println("请输入正确的选项数字!");
				break;
				
			}
					
		}
			
		
		
	}
	
/**
 * 登录，并根据不同权限去到不同的用户界面,并将对应的uvo传过去
 * 
 */
	public void loginShow() {
		UserVO uVo=null;
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("您现在的位置是：登录界面");
		while(true) {
		System.out.println("请输入用户名：");		
		try {
			String name=bReader.readLine();
			System.out.println("请输入密码：");
			String password=bReader.readLine();
			UserController uController=new UserController();
			uVo=uController.doLogin(name, password);
//			System.err.println("123123￥￥￥￥￥￥￥￥￥￥");
//			System.out.println(uVo);
			if(uVo!=null) {
				System.out.println("登录成功！");				
				if(uVo.getPower()==1) {
					System.out.println("------------12321");
					NormalFrame nFrame=new NormalFrame(uVo);
					nFrame.LoginSuccess();
				}
				else {		
					System.out.println("*************12321");
				AdminFrame aFrame=new AdminFrame(uVo);	
				aFrame.LoginSuccess();				
				}
				break;
				
			}
			
			else {
				System.out.println("登录失败，请检查用户名和密码是否正确！");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
		
		
	}

/**
 * 注册
 */
	public void registShow() {
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("您现在的位置是：注册界面");
		System.out.println("接下来请按照系统提示输入注册信息！");
		System.out.println("请输入6位用户名：");
		try {
			UserVO uVo =new UserVO();
			String username=bReader.readLine();
			System.out.println("请输入6位密码：");
			String password=bReader.readLine();
			System.out.println("请输入用户权限： 1为普通用户 2为管理员");
			int power=Integer.parseInt(bReader.readLine());			
			System.out.println("请输入姓名：");
			String name=bReader.readLine();
			System.out.println("请输入性别：");
			String sex=bReader.readLine();
			System.out.println("请输入出生日期 格式为 yyyy-MM-dd:");
			String birthday=bReader.readLine();
			System.out.println("请输入您的邮箱地址：");
			String email=bReader.readLine();
			if(TypeUtils.checkEmail(email)==false) {
				System.out.println("您输入的邮箱格式有误，请重新输入：");
				email=bReader.readLine();
			}
			uVo.setUsername(username);
			uVo.setPasswd(password);
			uVo.setPower(power);
			uVo.setName(name);
			uVo.setSex(sex);
			uVo.setBirthday(TypeUtils.strToDate(birthday));
			uVo.setEmail(email);
			UserController uController=new UserController();
			if(uController.doRegist(uVo)) {
				System.out.println("恭喜您注册成功！");
			}
			else
				System.out.println("注册失败！");		
			
			
		} catch (Exception e) {
			System.out.println("indexFrame 用户注册时出现错误"+e.getMessage());
			
		}

		
		
	}

	public void queryShow() {
	
		
	}


	public void updateShow() {
	
		
	}


	
	public void deleteShow() {
		
		
	}


}
