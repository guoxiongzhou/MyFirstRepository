package com.zjlb.mdif.entity;

import java.util.List;

public class ResultDto<T>
{
	private String messageCode;
	
	private String message;
	
	private List<T> result;

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
	

}
