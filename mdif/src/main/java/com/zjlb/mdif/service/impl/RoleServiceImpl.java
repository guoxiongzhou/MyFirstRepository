package com.zjlb.mdif.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zjlb.mdif.dao.RoleDao;
import com.zjlb.mdif.entity.Role;
import com.zjlb.mdif.entity.SystemUser;
import com.zjlb.mdif.entity.User;
import com.zjlb.mdif.service.RoleService;

public class RoleServiceImpl implements RoleService
{

	@Autowired
	private RoleDao roledao;

	public List<Role> selectRoleById(int id)
	{
		List<Role> list = roledao.selectRoleById(id);
		if (list.size() == 0)
		{
			return null;
		}
		return list;
	}

}
