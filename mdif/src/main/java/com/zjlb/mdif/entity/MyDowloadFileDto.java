package com.zjlb.mdif.entity;

public class MyDowloadFileDto
{
	private String fileId;

	private String region;

	private String hospital;
	
	private String fileName;
	
	private String monthText;
	
	private String userName;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getFileId()
	{
		return fileId;
	}

	public void setFileId(String fileId)
	{
		this.fileId = fileId;
	}

	public String getRegion()
	{
		return region;
	}

	public void setRegion(String region)
	{
		this.region = region;
	}

	public String getHospital()
	{
		return hospital;
	}

	public void setHospital(String hospital)
	{
		this.hospital = hospital;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getMonthText()
	{
		return monthText;
	}

	public void setMonthText(String monthText)
	{
		this.monthText = monthText;
	}
	
	
}
