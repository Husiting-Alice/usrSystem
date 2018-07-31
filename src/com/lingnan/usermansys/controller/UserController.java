package com.lingnan.usermansys.controller;
import com.lingnan.usermansys.business.service.UserService;
import com.lingnan.usermansys.business.service.UserServiceImple;
import com.lingnan.usermansys.usermgr.domain.UserVO;

public class UserController {
	//声明用户service对象，用于业务处理
	UserService userService=UserServiceImple.getInstance();	
	/*
	 * 用户登录
	 * @param name 用户名
	 * @param password 密码
	 * @return 用户信息
	 */
	public UserVO doLogin(String name,String password) {
		UserVO user=null;		
		try {
			//调用service接口中的login方法，进行用户登录操作。
			user=userService.login(name, password);			
		}catch(Exception e) {
			System.out.println("用户登录的时候出错了"+e.getMessage());
		}
		
		return user;
		
	}
	/*
	 * 用户注册
	 */
	
	/*
	 * 删除用户
	 */
	
	/*
	 * 修改用户信息
	 */
	
	
	
	

}
