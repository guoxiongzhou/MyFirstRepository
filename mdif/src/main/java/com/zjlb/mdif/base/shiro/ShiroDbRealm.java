package com.zjlb.mdif.base.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjlb.mdif.entity.Role;
import com.zjlb.mdif.entity.User;
import com.zjlb.mdif.service.impl.RoleServiceImpl;
import com.zjlb.mdif.service.impl.UserServiceImpl;

/**
 * 自定义的指定Shiro验证用户登录的类
 * 
 * @User:
 *
 */
@Service
public class ShiroDbRealm extends AuthorizingRealm
{

	private Logger logger = LoggerFactory.getLogger(getClass());

	public static final String SESSION_USER_KEY = "currentUser";

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private RoleServiceImpl roleService;

	// @Autowired
	// private MenuService menuService;

	/**
	 * 为当前登录的Subject授予角色和权限 经测试:本例中该方法的调用时机为需授权资源被访问时
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{

		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String currentAccount = (String) super.getAvailablePrincipal(principals);

		List<String> roleNameList = new ArrayList<String>();
		List<String> permissionList = new ArrayList<String>();

		// 从数据库中获取当前登录用户的详细信息
		User user = getUser();

		if (null != user)
		{

			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Role> roleList = roleService.selectRoleById(user.getRole());
			// List<Menu> menuList = menuService.selectMenuByUserId(map);

			/* 构建用户的角色集合 */
			for (Role role : roleList)
			{
				roleNameList.add(role.getName());
			}
			info.addRoles(roleNameList);

			/* 构建用户的权限代码集合 */
			/*
			 * for (Menu menu : menuList) { if
			 * (StringUtils.isNotBlank(menu.getPermission())) { if
			 * (StringUtil.isNotEmpty(menu.getPermission())) { String[]
			 * permissions = menu.getPermission().split(","); for (int i = 0; i
			 * < permissions.length; i++) { if
			 * (StringUtil.isNotEmpty(permissions[i])) {
			 * permissionList.add(permissions[i]); } } }
			 * 
			 * } }
			 */
			info.addStringPermissions(permissionList);

			return info;
		}
		else
		{
			return null;
		}

	}

	/**
	 * 验证当前登录的Subject
	 * 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.findByUserName(token.getUsername());

		if (user == null)
		{
			throw new UnknownAccountException("帐号找不到");
		}		

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),getName());
		setSession(SESSION_USER_KEY, user);
		return authenticationInfo;

	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value)
	{
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser)
		{
			Session session = currentUser.getSession();
			if (null != session)
			{
				session.setAttribute(key, value);
			}
		}
	}

	private User getUser()
	{
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(SESSION_USER_KEY);
		return user;
	}

	// 一定要写getset方法
	public UserServiceImpl getUserService()
	{
		return userService;
	}

	public void setUserService(UserServiceImpl userService)
	{
		this.userService = userService;
	}

	public RoleServiceImpl getRoleService()
	{
		return roleService;
	}

	public void setRoleService(RoleServiceImpl roleService)
	{
		this.roleService = roleService;
	}

}
