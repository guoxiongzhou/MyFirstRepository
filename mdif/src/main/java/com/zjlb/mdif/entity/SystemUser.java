package com.zjlb.mdif.entity;

import java.io.Serializable;

public class SystemUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5123417016189634504L;
	private int id;
	private String account;
	private String pwd;
	private String nickname;
	private char status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
