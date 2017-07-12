package com.zjlb.mdif.entity;

public class UploadFile {
	private String uploadId;

    private String userId;

    private String fileId;

    private String fileName;

    private String projectId;

    private int yearValue;
    
    private int monthValue;
     
	public int getYearValue()
	{
		return yearValue;
	}

	public void setYearValue(int yearValue)
	{
		this.yearValue = yearValue;
	}

	public int getMonthValue()
	{
		return monthValue;
	}

	public void setMonthValue(int monthValue)
	{
		this.monthValue = monthValue;
	}

	public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId == null ? null : uploadId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }
   
}
