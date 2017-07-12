package com.zjlb.mdif.entity;

public class Hospital
{
	private int hospitalId;

	private String hospitalName;

	public int getHospitalId()
	{
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId)
	{
		this.hospitalId = hospitalId;
	}

	public String getHospitalName()
	{
		return hospitalName;
	}

	public void setHospitalName(String hospitalName)
	{
		this.hospitalName = hospitalName == null ? null : hospitalName.trim();
	}
}