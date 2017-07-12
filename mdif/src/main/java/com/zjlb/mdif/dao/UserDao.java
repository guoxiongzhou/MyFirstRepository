package com.zjlb.mdif.dao;

import org.springframework.stereotype.Component;

import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.User;

import java.util.List;

@Component
public interface UserDao 
{
	int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByUserName(String userName);
    
    List<User> selectOperatorByProjectId(String projectId);
    
    User selectProjectManager(String projectId);
    
    List<ProjectListDto> selectAllProjects();    
    
    List<ProjectListDto> searchProjects(String projectName);
    
    List<ProjectListDto> selectProject(String projectId);
    
    
}