package com.lingnan.usermansys.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.lingnan.usermansys.controller.UserController;

public class IndexFrame implements BaseFrame{

	@Override
	public void show() {
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("     欢迎使用用户管理系统          ");
			System.out.println("-----请选择您需要的操作-----");
			System.out.println("--------登录系统---------1");
			System.out.println("--------新用户注册--------2");
			System.out.println("--------退出系统---------0");
			int i=-1;
			while(true) {
				try {
				i=Integer.parseInt(scanner.nextLine());
//				if(i!=1||i!=2||i!=0)
//					System.out.println("：");
//				else					
				break;
				}catch (Exception e) {
					System.out.println("从控制台接收数据时出错-index"+e.getMessage());
				}
			}
			
			switch(i) {
			case 1:
				this.loginShow();				
			case 2:
				this.registShow();
			case 0:
				System.out.println("感谢使用本系统，再会！");
				System.exit(0);		
				//System.exit(0)是正常退出程序，而System.exit(1)或者说非0表示非正常退出程序
			default:
				System.out.println("请输入正确的选项数字!");
				
				
				
				
			}
			
		}
		
		
		
		
	}
	

	public void loginShow() {
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("您现在的位置是：登录界面");
		System.out.println("请输入用户名：");
		try {
			String name=bReader.readLine();
			System.out.println("请输入密码：");
			String password=bReader.readLine();
			UserController uController=new UserController();
			uController.doLogin(name, password);
			if(uController!=null) {
				System.out.println(uController);
				System.out.println(name);
				System.out.println(password);
				System.out.println("登录成功！");
//				AdminFrame aFrame=new AdminFrame();			
				
				System.exit(0);
			}
			else 
				System.out.println("登录失败，请检查用户名和密码是否正确！");
	
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
	}


	public void registShow() {
		
		
	}

	public void queryShow() {
	
		
	}


	public void updateShow() {
	
		
	}


}
