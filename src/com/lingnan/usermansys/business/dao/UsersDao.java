package com.lingnan.usermansys.business.dao;
import com.lingnan.usermansys.comm.dao.BaseDao;
import com.lingnan.usermansys.usermgr.domain.UserVO;

public interface UsersDao extends BaseDao{	
	/**
	 * 登录，输入用户名和密码
	 */
	public UserVO login(String username,String password);
	/**
	 * 注册，添加新用户，参数为持久层实体类对象
	 */
	public boolean regist(UserVO usr);
	/**
	 * 删除指定用户（管理员才有此权限)，根据用户名删除
	 */
	public boolean deleteUser(String username);
	/**
	 * 更新指定用户信息（普通用户只能更新自己的信息），参数为持久层实体类，因为管理员更新指定用户的时候需要获得该用户的类对象
	 */
	public boolean updateUser(UserVO usr,String username);
	/**
	 * 查询所有用户信息，密码经过加密（管理员有此权限）
	 * 分页查询所有用户信息
	 * page为第几页
	 * row为每页显示几行
	 */
	
	public UserVO findAllUser(int page,int row);
	
	/**
	 * 
	 * 查询指定用户信息
	 * 参数为该用户的用户名
	 */
	public UserVO findOneUser(String username);
	
	/**
	 * 
	 * 模糊查询用户信息，参数为查询关键字
	 */
	public UserVO fuzzyQuery(String keyword);
	


}
