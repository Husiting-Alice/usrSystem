package com.lingnan.usermansys.view;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.lingnan.usermansys.comm.util.TypeUtils;
import com.lingnan.usermansys.controller.UserController;
import com.lingnan.usermansys.usermgr.domain.UserVO;
/**
 *  用户在IndexFrame中登录之后，进行权限判断，如果是普通用户，则调用普通用户LoginSuccess方法
 * 提示用户权限信息，并进行后续操作
 * @author 123
 *
 */
public class NormalFrame extends IndexFrame{

	UserVO uVo =null;
	/*
	 * 带参数的构造器，用于初始化user属性
	 */
	public NormalFrame(UserVO uVo) {
		this.uVo=uVo;		
	}
	
	/*
	 * 定义一个无参构造器
	 */
	public NormalFrame() {}
	
	public void LoginSuccess() {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String identity=null;
		if(uVo.getPower()==1) 								
			identity="普通用户";			
		else
			identity="管理员";		
		if(uVo.getPower()==1) {
		System.out.println(uVo.getName()+",欢迎登录本用户管理系统");
		System.out.println("您的身份是:"+identity);
//		System.out.println("12321");
		}
		
		while(true) {
			System.out.println("请选择您需要的操作（请输入对应的数字）：");
			System.out.println("-----查询个人信息------1-------");
			System.out.println("-----修改个人信息------2-------");
			System.out.println("-----退出系统---------3-------");
			int i=-1;
			while(true) {
				try {
				i=Integer.parseInt(br.readLine());
				break;
				}catch (Exception e) {
					System.out.println("Normalindex 出错"+e.getMessage());
				}
			}
			UserController uController=new UserController();
			switch (i) {
			case 1:
				uController.doQueryByUserName(uVo.getUsername());
				break;
			case 2:	
				this.updateShow();
			case 3:
				System.out.println("您已成功退出！");
				System.exit(0);
			default:
				break;
			}
			
		}
		
	}
	public void updateShow() {
		System.out.println("您现在的位置是：更新您的用户的信息");
//		Scanner scanner=new Scanner(System.in);
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
			while (true) {
//			System.out.println("请输入您要修改的用户的 用户名：");
			try {
				String username=uVo.getUsername();
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
	}
	}
	
	
