package com.zjlb.mdif.service;

import java.util.List;

import com.zjlb.mdif.entity.CommonEnum.UploadStatus;
import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.ProjectListWithPageDto;
import com.zjlb.mdif.entity.User;

/**
 * 总管理员
 * @author Administrator
 *
 */
public interface MainManagerService
{
	/**
	 * 总管理员获取项目信息列表
	 * @param user 当前用户，用户权限判断
	 * @param uploadStatus 上传状态，作为查询条件
	 * @return
	 */
	ProjectListWithPageDto selectAllProjects(User user,UploadStatus uploadStatus,int pageNumber,int pageSize);
    
    /**
	 * 项目管理员获取上传信息列表
	 * @param user 当前用户，用户权限判断
	 * @param monthText 上传状态，作为查询条件
	 * @return
	 */
    ProjectListWithPageDto selectMyProject(User user, String monthText,int pageNumber,int pageSize);
    
    /**
     * 总管理员根据项目名称查询项目信息列表
     * @param projectName
     * @return
     */
    List<ProjectListDto> searchProjects(String projectName,User user);

}
