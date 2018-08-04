package com.lingnan.usermansys.controller;
import com.lingnan.usermansys.business.service.UserService;
import com.lingnan.usermansys.business.service.UserServiceImple;
import com.lingnan.usermansys.usermgr.domain.UserVO;
public class UserController {
	/**
	 * 声明用户service对象，用于业务处理
	 */
	UserService userService=UserServiceImple.getInstance();	
	/**
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
			System.out.println("controller 用户登录的时候出错了"+e.getMessage());
		}
		
		return user;
		
	}
	/**
	 * 用户注册
	 * @param uVo 
	 * @return
	 */
	 
	public boolean doRegist(UserVO uVo) {
		boolean flag=false;
		try {
			flag=userService.regist(uVo);
			
		} catch (Exception e) {
			System.out.println("用户注册的时候出错了，请检查您输入的格式是否符合要求"+e.getMessage());
			
		}
		
		return flag;
	}
	
	
	/*
	 * 删除用户
	 */
	public boolean doDelete(String username) {
		boolean flag=false;
		try {
			flag=userService.deleteUser(username);
			
		} catch (Exception e) {
			System.out.println("controller 删除用户的时候出错了"+e.getMessage());
		}
		
		return flag;
		
	}
	
	
	/**
	 * 修改用户信息
	 * @param usr 实体类对象
	 * @param username 用户名
	 * @return 布尔值
	 */
	 
	
	public boolean UpdateUserByUsrName(UserVO usr,String username) {
		boolean flag=false;
		try {
		flag=userService.updateUser(usr, username);
		}catch (Exception e) {
			System.out.println("controller 修改用户的时候出错了"+e.getMessage());
		}
		return flag;
	}


	
	/**
	 * 模糊查询
	 * @param keyword 查询关键字
	 * @return 实体类对象
	 */
	public UserVO doFuzzyQuery(String keyword) {
		UserVO uVo=null;
		try {
			uVo=userService.fuzzyQuery(keyword);
		}catch (Exception e) {
			System.out.println("controller 模糊查询用户的时候出错了"+e.getMessage());
		}
		
		return uVo;		
	}
	
	/**
	 * 
	 * @param username 按照用户名查询
	 * @return 实体类对象
	 */
	public UserVO doQueryByUserName(String username) {
		UserVO uVo=null;
		try {
			uVo=userService.findOneUser(username);
			
		}catch (Exception e) {
			System.out.println("controller 查询指定用户的时候出错了"+e.getMessage());
		}
		
		return uVo;	
	}
	/**
	 * 分页查询
	 * @param page 第几页
	 * @param row 每页显示的行数
	 * @return 实体类对象
	 */
	public UserVO findAllInfo(int page,int row) {
		UserVO uVo=null;
		try {
			uVo=userService.findAllUser(page, row);
			
		}catch (Exception e) {
			System.out.println("controller 分页查询的时候出错了"+e.getMessage());
		}
		
		return uVo;	
		
	}


}
