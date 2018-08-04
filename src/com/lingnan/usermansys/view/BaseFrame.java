package com.lingnan.usermansys.view;

import com.lingnan.usermansys.usermgr.domain.UserVO;

/**
 * 定义视图层的一个父接口，在这里定义了视图层要用到的方法
 * @author 123
 *
 */
public interface BaseFrame {
	/**
	 * 页面展示
	 */
	public void show();
	
	/**
	 * 登录页面展示
	 */
	public void loginShow();
	
	
	/**
	 * 用户注册页面显示 
	 */
	
	public void registShow();
	
	/**
	 * 查询
	 * 
	 */
	
	public void queryShow();
	
	
	/**
	 *更新 
	 */
	
	public void updateShow();
	
    /**
     * 删除用户
     */
	
	public void deleteShow();

}
