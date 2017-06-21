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
 * �ܹ���Ա��Ӧ��ҵ���߼�
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
	 * �ܹ���Ա��ȡ��Ŀ��Ϣ�б�
	 */
	@Override
	public List<ProjectListDto> selectAllProjects(String userId)
	{
		User user = userDao.selectByPrimaryKey(userId);		
		//�ж��Ƿ�Ϊ�ܹ���Ա
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
	 * �ж��û��Ƿ�Ϊ�ܹ���Ա
	 * @param user
	 * @return
	 */
	private boolean isMainManager(User user)
	{
		return user.getRole() == (byte)0;
	}
	
	 /**
     * �ܹ���Ա������Ŀ���Ʋ�ѯ��Ŀ��Ϣ�б�
     * @param projectName
     * @return
     */
	@Override
	public List<ProjectListDto> searchProjects(String projectName,String userId)
	{	
		User user = userDao.selectByPrimaryKey(userId);		
		//�ж��Ƿ�Ϊ�ܹ���Ա
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
