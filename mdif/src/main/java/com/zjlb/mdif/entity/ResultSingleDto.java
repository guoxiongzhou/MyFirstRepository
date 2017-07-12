package com.zjlb.mdif.entity;


public class ResultSingleDto<T>
{
    private String messageCode;
	
	private String message;
	
	private T result;

	public String getMessageCode()
	{
		return messageCode;
	}

	public void setMessageCode(String messageCode)
	{
		this.messageCode = messageCode;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public T getResult()
	{
		return result;
	}

	public void setResult(T result)
	{
		this.result = result;
	}
	
	
}
