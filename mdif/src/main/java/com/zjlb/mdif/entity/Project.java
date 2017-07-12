package com.zjlb.mdif.entity;

public class Project {
	private String projectId;

    private String name;

    private int startYear;
    
    private int startMonth;
    
    public int getStartYear()
	{
		return startYear;
	}

	public void setStartYear(int startYear)
	{
		this.startYear = startYear;
	}

	public int getStartMonth()
	{
		return startMonth;
	}

	public void setStartMonth(int startMonth)
	{
		this.startMonth = startMonth;
	}
    
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	
    
    
}
