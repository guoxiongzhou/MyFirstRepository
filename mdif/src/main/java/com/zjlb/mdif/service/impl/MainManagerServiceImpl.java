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
 * �ܹ���Ա��Ӧ��ҵ���߼�
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
	 * �ܹ���Ա��ȡ��Ŀ��Ϣ�б�
	 */
	@Override
	public List<ProjectListDto> selectAllProjects()
	{
		return userdao.selectAllProjects();
	}

	 /**
     * �ܹ���Ա������Ŀ���Ʋ�ѯ��Ŀ��Ϣ�б�
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
