package com.zjlb.mdif.entity;

/**
 * 项目信息列表DTO用户界面呈现
 * @author Administrator
 *
 */
public class ProjectListDto 
{	
	private String projectId;	
	
	private String projectName;
	
	private String region;
	
	private String hospital;
	
	/**
	 * 年月
	 */
	private String monthText;
	
	/**
	 * 是否已上传
	 */
	private String uploadStatus;
	
	/**
	 * 项目管理员
	 */
	private String projectManager;

	public String getProjectName()
	{
		return projectName;
	}

	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
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

	public String getMonthText()
	{
		return monthText;
	}

	public void setMonthText(String monthText)
	{
		this.monthText = monthText;
	}

	public String getUploadStatus()
	{
		return uploadStatus;
	}

	public void setUploadStatus(String uploadStatus)
	{
		this.uploadStatus = uploadStatus;
	}

	public String getProjectManager()
	{
		return projectManager;
	}

	public void setProjectManager(String projectManager)
	{
		this.projectManager = projectManager;
	}

	public String getProjectId()
	{
		return projectId;
	}

	public void setProjectId(String projectId)
	{
		this.projectId = projectId;
	}
	

}
