package com.zjlb.mdif.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zjlb.mdif.dao.UserDao;
import com.zjlb.mdif.entity.ProjectListDto;
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
	private UserDao userdao;
	
	/**
	 * 总管理员获取项目信息列表
	 */
	@Override
	public List<ProjectListDto> selectAllProjects()
	{
		return userdao.selectAllProjects();
	}

	 /**
     * 总管理员根据项目名称查询项目信息列表
     * @param projectName
     * @return
     */
	@Override
	public List<ProjectListDto> searchProjects(String projectName)
	{		
		if(!StringUtils.isEmpty(projectName))
		{
			return userdao.searchProjects(projectName);
		}
		else
		{
			return selectAllProjects();
		}
	}

}
