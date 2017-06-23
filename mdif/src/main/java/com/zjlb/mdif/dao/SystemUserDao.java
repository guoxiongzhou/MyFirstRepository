package com.zjlb.mdif.dao;

import org.springframework.stereotype.Component;

import com.zjlb.mdif.entity.SystemUser;

import java.util.List;

@Component
public interface SystemUserDao
{
	List<SystemUser> selectId(String username);
}