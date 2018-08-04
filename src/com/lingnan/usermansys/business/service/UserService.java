package com.lingnan.usermansys.business.service;
import com.lingnan.usermansys.usermgr.domain.UserVO;
public interface UserService {

	/**
	 * 登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 实体类对象
	 */
	public UserVO login(String username,String password);
	/**
	 * 注册，添加新用户
	 * @param usr 实体类对象
	 * @return 布尔值
	 */
	public boolean regist(UserVO usr);
	/**
	 * 删除指定用户（管理员才有此权限)
	 * @param username 用户名
	 * @return 布尔值
	 */
	public boolean deleteUser(String username);
	/**
	 * 	  更新指定用户信息（普通用户只能更新自己的信息）
	 * @param usr 实体类对象
	 * @param passwd 用户名
	 * @return 布尔值
	 */
	 
	public boolean updateUser(UserVO usr,String passwd);
	/**
	 * 查询所有用户信息，密码经过加密（管理员有此权限）
	 * 分页查询所有用户信息
	 */
	 
	 
	
	public UserVO findAllUser(int page,int row);
	
	/**
	 * 查询指定用户信息
	 * @param username 用户名
	 * @return 实体类对象
	 */
	public UserVO findOneUser(String username);
	
	/**
	 * 模糊查询用户信息
	 * @param keyword 模糊查询的关键字
	 * @return
	 */
	
	public UserVO fuzzyQuery(String keyword);
}
