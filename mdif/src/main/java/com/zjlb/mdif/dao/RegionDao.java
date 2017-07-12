package com.zjlb.mdif.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.zjlb.mdif.entity.Region;

@Component
public interface RegionDao
{
	int deleteByPrimaryKey(int regionId);

	int insert(Region record);

	int insertSelective(Region record);

	Region selectByPrimaryKey(Integer regionId);

	int updateByPrimaryKeySelective(Region record);

	int updateByPrimaryKey(Region record);
	
	List<Region> selectAll();
}