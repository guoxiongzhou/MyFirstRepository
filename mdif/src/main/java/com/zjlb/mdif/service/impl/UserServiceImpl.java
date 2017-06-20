package com.zjlb.mdif.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zjlb.mdif.dao.UserDao;
import com.zjlb.mdif.entity.User;
import com.zjlb.mdif.service.UserService;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userdao;

	public User doUserLogin(User user) {
		User myUser = userdao.selectByUserName(user.getUserName());
		if (myUser == null) {
			return null;
		} else {
			if (user.getPassword().equals(myUser.getPassword())) {
				return myUser;
			} else {
				return null;
			}
		}
	}

}
