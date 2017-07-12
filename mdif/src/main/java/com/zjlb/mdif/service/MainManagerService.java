package com.zjlb.mdif.service;

import java.util.List;

import com.zjlb.mdif.entity.CommonEnum.UploadStatus;
import com.zjlb.mdif.entity.ProjectListDto;
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
    List<ProjectListDto> selectAllProjects(User user,UploadStatus uploadStatus);
    
    /**
     * �ܹ���Ա������Ŀ���Ʋ�ѯ��Ŀ��Ϣ�б�
     * @param projectName
     * @return
     */
    List<ProjectListDto> searchProjects(String projectName,User user);

}
