package com.zjlb.mdif.entity;

public class CommonEnum
{
	/**
     * 角色
     * 3,省中心总管理员；2，省中心项目管理员；1，各区域管理员。
     */
	public enum UserType
	{ 
		/**
		 * 非法用户
		 */
		NONE,
		 /**
	     * 区域操作员
	     */
	    OPERATOR,
	    /**
	     * 项目管理员
	     */
	    PROJECT_MANAGER,
		/**
		 * 总管理员
		 */
	    MAIN_MANAGER
	    
	   
	}

	/**
	 * 上传状态
	 * @author Administrator
	 *
	 */
    public enum UploadStatus
    {
    	NON,
    	/**
    	 * 已上传
    	 */
    	UPLOADED,
    	/**
    	 * 未上传
    	 */
    	UNUPLOAD
    }
}


