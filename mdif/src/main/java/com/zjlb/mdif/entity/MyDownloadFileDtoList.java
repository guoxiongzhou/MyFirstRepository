package com.zjlb.mdif.entity;

import java.util.List;

public class MyDownloadFileDtoList
{
	private int total;

	private List<MyDowloadFileDto> rows;

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public List<MyDowloadFileDto> getRows()
	{
		return rows;
	}

	public void setRows(List<MyDowloadFileDto> rows)
	{
		this.rows = rows;
	}
	
	
}
