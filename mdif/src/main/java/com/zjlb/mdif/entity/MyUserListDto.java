package com.zjlb.mdif.entity;

import java.util.List;

/**
 * ��Ŀ����Ա��Ӧ�Ĳ���Ա�б�
 * @author Administrator
 *
 */
public class MyUserListDto
{
	private int total;
	
	private List<MyUserDto> rows;

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public List<MyUserDto> getRows()
	{
		return rows;
	}

	public void setRows(List<MyUserDto> rows)
	{
		this.rows = rows;
	}
	
	

}
