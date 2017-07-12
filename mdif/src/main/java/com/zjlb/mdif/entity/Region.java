package com.zjlb.mdif.entity;

public class Region
{
	private int regionId;

	private String regionName;

	public int getRegionId()
	{
		return regionId;
	}

	public void setRegionId(Integer regionId)
	{
		this.regionId = regionId;
	}

	public String getRegionName()
	{
		return regionName;
	}

	public void setRegionName(String regionName)
	{
		this.regionName = regionName == null ? null : regionName.trim();
	}
}