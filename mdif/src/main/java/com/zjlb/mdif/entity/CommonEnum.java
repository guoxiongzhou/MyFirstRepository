package com.zjlb.mdif.entity;

public class CommonEnum
{
	/**
     * 角色
     * 0,省中心总管理员；1，省中心项目管理员；2，各区域管理员。
     */
	public enum UserType
	{ 
		/**
		 * 非法用户
		 */
		NONE,
		/**
		 * 总管理员
		 */
	    MAIN_MANAGER,
	    /**
	     * 项目管理员
	     */
	    PROJECT_MANAGER,
	    /**
	     * 区域操作员
	     */
	    OPERATOR
	}

}


