package com.zjlb.mdif.service;

import java.util.List;

import com.zjlb.mdif.entity.CommonEnum.UserType;
import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.User;

public interface UserService
{
	User doUserLogin(User user);

	/**
	 * 获取用户类型
	 * 
	 * @param userId
	 * @return
	 */
	UserType getUserType(String userId);
	
	
	User findByUserName(String username);
	
	/**
	 * 获取用户类型
	 * 
	 * @param userId
	 * @return
	 */
	UserType getUserType(User user);

}
