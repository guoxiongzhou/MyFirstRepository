package com.zjlb.mdif.entity;

public class CommonEnum
{
	/**
     * ��ɫ
     * 3,ʡ�����ܹ���Ա��2��ʡ������Ŀ����Ա��1�����������Ա��
     */
	public enum UserType
	{ 
		/**
		 * �Ƿ��û�
		 */
		NONE,
		 /**
	     * �������Ա
	     */
	    OPERATOR,
	    /**
	     * ��Ŀ����Ա
	     */
	    PROJECT_MANAGER,
		/**
		 * �ܹ���Ա
		 */
	    MAIN_MANAGER
	    
	   
	}

	/**
	 * �ϴ�״̬
	 * @author Administrator
	 *
	 */
    public enum UploadStatus
    {
    	NON,
    	/**
    	 * ���ϴ�
    	 */
    	UPLOADED,
    	/**
    	 * δ�ϴ�
    	 */
    	UNUPLOAD
    }
}


