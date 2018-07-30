package com.lingnan.usermansys.business.service;
import com.lingnan.usermansys.usermgr.domain.UserVO;
public interface DaoService {
	//登录
	public UserVO login(String username,String password);
	//注册，添加新用户
	public boolean regist(UserVO usr);
	//删除指定用户（管理员才有此权限)
	public boolean deleteUser(String username);
	//更新指定用户信息（普通用户只能更新自己的信息）
	public boolean updateUser();
	//查询所有用户信息，密码经过加密（管理员有此权限）
	public UserVO findAllUser();
}
