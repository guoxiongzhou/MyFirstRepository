package com.zjlb.mdif.entity;

import java.util.List;

public class ProjectListWithPageDto
{
	private int total;
	
	private List<ProjectListDto> rows;

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public List<ProjectListDto> getRows()
	{
		return rows;
	}

	public void setRows(List<ProjectListDto> rows)
	{
		this.rows = rows;
	}
	
	

}
