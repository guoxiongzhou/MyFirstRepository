package com.zjlb.mdif.entity;

/**
 * 
 * @author Administrator
 *
 */
public class MyUserDto
{
	private String id;

	private String region;

	private String hospital;

	private String userName;

	private String password;

	/*private String operator;

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}
*/
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
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

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

}
