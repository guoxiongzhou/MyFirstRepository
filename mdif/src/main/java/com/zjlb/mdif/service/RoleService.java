package com.zjlb.mdif.service;

import java.util.List;
import com.zjlb.mdif.entity.Role;

public interface RoleService 
{
	List<Role> selectRoleById(int id);
}
