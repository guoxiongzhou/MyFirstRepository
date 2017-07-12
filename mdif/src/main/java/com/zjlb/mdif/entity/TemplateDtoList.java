package com.zjlb.mdif.entity;

import java.util.List;

public class TemplateDtoList
{
	private int total;

	private List<TemplateDto> rows;

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public List<TemplateDto> getRows()
	{
		return rows;
	}

	public void setRows(List<TemplateDto> rows)
	{
		this.rows = rows;
	}
	
	
}
