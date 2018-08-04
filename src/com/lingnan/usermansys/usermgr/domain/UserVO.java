package com.lingnan.usermansys.usermgr.domain;
import java.util.Date;
/**
 * 持久层 实体类
 */
public class UserVO {
	String username; /**用户名*/
	String passwd; /**密码*/
	int power; /**权限*/
	String name; /**姓名*/
	String sex; /**性别*/
	Date birthday; /**生日*/
	String email; /**邮箱地址*/
	int status; /**状态*/
	/**
	 * 获取用户名
	 * @return 返回String 类型数据
	 */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取密码
	 * @return 返回String 类型数据
	 */
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	/**
	 * 获取权限
	 * @return 返回int 类型数据
	 */
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	/**
	 * 获取姓名
	 * @return 返回String 类型数据
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取性别
	 * @return 返回String 类型数据
	 */
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取生日
	 * @return 返回Date 类型数据
	 */
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取右键地址
	 * @return 返回String 类型数据
	 */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取删除状态
	 * @return 返回int 类型数据
	 */
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

}
