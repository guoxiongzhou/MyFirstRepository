package com.zjlb.mdif.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zjlb.mdif.dao.UserDao;
import com.zjlb.mdif.entity.CommonEnum.UserType;
import com.zjlb.mdif.entity.User;
import com.zjlb.mdif.service.UserService;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserDao userDao;

	public User doUserLogin(User user)
	{
		User myUser = userDao.selectByUserName(user.getUserName());
		if (myUser == null)
		{
			return null;
		}
		else
		{
			if (user.getPassword().equals(myUser.getPassword()))
			{
				return myUser;
			}
			else
			{
				return null;
			}
		}
	}

	@Override
	public UserType getUserType(String userId)
	{
		User user = userDao.selectByPrimaryKey(userId);
		if (user == null)
		{
			return UserType.NONE;
		}
		else
		{
			return getUserType(user);
			
		}
	}	
	

	@Override
	public UserType getUserType(User user)
	{
		switch(user.getRole())
		{
			case 3:
				return UserType.MAIN_MANAGER;
			case 2:
				return UserType.PROJECT_MANAGER;
			default:
				return UserType.OPERATOR;	
		}
	}

	@Override
	public User findByUserName(String username)
	{
		return userDao.selectByUserName(username);
	}

}
