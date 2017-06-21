package com.zjlb.mdif.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zjlb.mdif.dao.UserDao;
import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.User;
import com.zjlb.mdif.service.MainManagerService;

/**
 * 总管理员对应的业务逻辑
 * @author Administrator
 *
 */
@Service
@EnableTransactionManagement
public class MainManagerServiceImpl implements MainManagerService
{

	@Autowired
	private UserDao userDao;
	
	/**
	 * 总管理员获取项目信息列表
	 */
	@Override
	public List<ProjectListDto> selectAllProjects(String userId)
	{
		User user = userDao.selectByPrimaryKey(userId);		
		//判断是否为总管理员
		if(user != null && isMainManager(user))
		{
			return userDao.selectAllProjects();
		}
		else
		{
			return null;
		}
	}

	/**
	 * 判断用户是否为总管理员
	 * @param user
	 * @return
	 */
	private boolean isMainManager(User user)
	{
		return user.getRole() == (byte)0;
	}
	
	 /**
     * 总管理员根据项目名称查询项目信息列表
     * @param projectName
     * @return
     */
	@Override
	public List<ProjectListDto> searchProjects(String projectName,String userId)
	{	
		User user = userDao.selectByPrimaryKey(userId);		
		//判断是否为总管理员
		if(user != null && isMainManager(user))
		{
			return userDao.searchProjects(projectName);
		}
		else
		{
			return null;
		}		
	}

}
