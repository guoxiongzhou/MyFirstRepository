package com.zjlb.mdif.service;

import java.util.List;

import com.zjlb.mdif.entity.ProjectListDto;

/**
 * 总管理员
 * @author Administrator
 *
 */
public interface MainManagerService
{
	/**
	 * 总管理员获取项目信息列表
	 * @return
	 */
    List<ProjectListDto> selectAllProjects();
    
    /**
     * 总管理员根据项目名称查询项目信息列表
     * @param projectName
     * @return
     */
    List<ProjectListDto> searchProjects(String projectName);

}
