package com.zjlb.mdif.dao;

import java.util.List;

import com.zjlb.mdif.entity.Role;

public interface RoleDao
{
	List<Role> selectRoleById(int userid);
}
