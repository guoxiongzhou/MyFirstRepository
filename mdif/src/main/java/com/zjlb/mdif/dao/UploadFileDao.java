package com.zjlb.mdif.dao;

import com.zjlb.mdif.entity.UploadFile;

public interface UploadFileDao 
{
	int deleteByPrimaryKey(String uploadId);

    int insert(UploadFile record);

    int insertSelective(UploadFile record);

    UploadFile selectByPrimaryKey(String uploadId);

    int updateByPrimaryKeySelective(UploadFile record);

    int updateByPrimaryKey(UploadFile record);
}
