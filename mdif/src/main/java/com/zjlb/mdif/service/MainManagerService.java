package com.zjlb.mdif.service;

import java.util.List;

import com.zjlb.mdif.entity.ProjectListDto;

/**
 * �ܹ���Ա
 * @author Administrator
 *
 */
public interface MainManagerService
{
	/**
	 * �ܹ���Ա��ȡ��Ŀ��Ϣ�б�
	 * @return
	 */
    List<ProjectListDto> selectAllProjects();
    
    /**
     * �ܹ���Ա������Ŀ���Ʋ�ѯ��Ŀ��Ϣ�б�
     * @param projectName
     * @return
     */
    List<ProjectListDto> searchProjects(String projectName);

}
