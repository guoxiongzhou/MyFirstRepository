package com.zjlb.mdif.service;

import java.util.List;

import com.zjlb.mdif.entity.CommonEnum.UploadStatus;
import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.ProjectListWithPageDto;
import com.zjlb.mdif.entity.User;

/**
 * �ܹ���Ա
 * @author Administrator
 *
 */
public interface MainManagerService
{
	/**
	 * �ܹ���Ա��ȡ��Ŀ��Ϣ�б�
	 * @param user ��ǰ�û����û�Ȩ���ж�
	 * @param uploadStatus �ϴ�״̬����Ϊ��ѯ����
	 * @return
	 */
	ProjectListWithPageDto selectAllProjects(User user,UploadStatus uploadStatus,int pageNumber,int pageSize);
    
    /**
	 * ��Ŀ����Ա��ȡ�ϴ���Ϣ�б�
	 * @param user ��ǰ�û����û�Ȩ���ж�
	 * @param monthText �ϴ�״̬����Ϊ��ѯ����
	 * @return
	 */
    ProjectListWithPageDto selectMyProject(User user, String monthText,int pageNumber,int pageSize);
    
    /**
     * �ܹ���Ա������Ŀ���Ʋ�ѯ��Ŀ��Ϣ�б�
     * @param projectName
     * @return
     */
    List<ProjectListDto> searchProjects(String projectName,User user);

}
