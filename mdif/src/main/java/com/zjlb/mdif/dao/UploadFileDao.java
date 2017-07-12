package com.zjlb.mdif.dao;

import java.util.List;

import com.zjlb.mdif.entity.MyDowloadFileDto;
import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.UploadFile;

public interface UploadFileDao 
{
	int deleteByPrimaryKey(String uploadId);

    int insert(UploadFile record);

    int insertSelective(UploadFile record);

    UploadFile selectByPrimaryKey(String uploadId);

    int updateByPrimaryKeySelective(UploadFile record);

    int updateByPrimaryKey(UploadFile record);
    
    List<MyDowloadFileDto> selectByProjectId(String projectId);
    
    List<UploadFile> selectAll();
    
    List<UploadFile> selectByUserId(String userId);
    
    List<ProjectListDto> selectAllUploaded();
}
