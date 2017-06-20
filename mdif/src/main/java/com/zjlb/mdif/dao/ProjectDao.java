package com.zjlb.mdif.dao;

import org.springframework.stereotype.Component;

import com.zjlb.mdif.entity.Project;

@Component
public interface ProjectDao
{
	int deleteByPrimaryKey(String projectId);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(String projectId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}
