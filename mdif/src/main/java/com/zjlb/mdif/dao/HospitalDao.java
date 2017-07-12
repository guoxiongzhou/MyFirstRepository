package com.zjlb.mdif.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zjlb.mdif.entity.Hospital;

@Component
public interface HospitalDao
{
	int deleteByPrimaryKey(int hospitalId);

	int insert(Hospital record);

	int insertSelective(Hospital record);

	Hospital selectByPrimaryKey(Integer hospitalId);

	int updateByPrimaryKeySelective(Hospital record);

	int updateByPrimaryKey(Hospital record);
	
	List<Hospital> selectAll();
}