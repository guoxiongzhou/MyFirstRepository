package com.zjlb.mdif.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjlb.mdif.dao.ProjectDao;
import com.zjlb.mdif.entity.Project;
import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.User;
import com.zjlb.mdif.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController
{
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectDao projectDao;

	@RequestMapping("/dologin.do")
	public String dologin(User user, Model model)
	{
		logger.info("login ....");
		User dbuser = userService.doUserLogin(user);
		if (dbuser == null)
		{
			model.addAttribute("failMsg", "用户或密码错误!");
			return "/fail";
			
		}
		else
		{
			String info = loginUser(user);
			if (!"SUCC".equals(info))
			{
				model.addAttribute("failMsg", "用户或密码错误");
				return "/fail";				
			}
			else
			{
				return "redirect:getMyPage";
			}
		}
	}	

	@RequestMapping("/logout.do")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Subject subject = SecurityUtils.getSubject();
		if (subject != null)
		{
			try
			{
				subject.logout();
			}
			catch (Exception ex)
			{
			}
		}
		response.sendRedirect("/mdif/index.jsp");
	}

	private String loginUser(User user)
	{
		if (isRelogin(user))
			return "SUCC"; // 如果已经登陆，无需重新登录

		return shiroLogin(user); // 调用shiro的登陆验证
	}

	private String shiroLogin(User user)
	{
		// 组装token，包括客户公司名称、简称、客户编号、用户名称；密码
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword().toCharArray(),
				null);
		token.setRememberMe(true);

		// shiro登陆验证
		try
		{
			SecurityUtils.getSubject().login(token);
		}
		catch (UnknownAccountException ex)
		{
			return "用户不存在或者密码错误！";
		}
		catch (IncorrectCredentialsException ex)
		{
			return "用户不存在或者密码错误！";
		}
		catch (AuthenticationException ex)
		{
			return ex.getMessage(); // 自定义报错信息
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return "内部错误，请重试！";
		}
		return "SUCC";
	}

	private boolean isRelogin(User user)
	{
		Subject us = SecurityUtils.getSubject();
		if (us.isAuthenticated())
		{
			return true; // 参数未改变，无需重新登录，默认为已经登录成功
		}
		return false; // 需要重新登陆
	}

	/**
	 * 根据用户类型跳转到指定的页面
	 * 
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/getMyPage")
	public String getMyPage(Model model,HttpSession httpSession, HttpServletResponse response)
	{
		try
		{
			User user = (User) httpSession.getAttribute("currentUser");
			if(user == null)
			{
				model.addAttribute("failMsg", "没有权限访问");
				response.sendRedirect("/mdif/index.jsp");
				return null;
			}
			else
			{
				switch (userService.getUserType(user))
				{
					case MAIN_MANAGER:
						return "/admin";
					case PROJECT_MANAGER:
						setProjectName(user,model);
						return "/manager";
					case OPERATOR:
						setProjectName(user,model);
						return "/normal";
					default:
						model.addAttribute("failMsg", "没有权限访问");
						response.sendRedirect("/mdif/index.jsp");
						return null;
				}
			}			
			
		}
		catch(Exception ex)
		{
			logger.error("getMyPage: " + ex.getMessage());
			model.addAttribute("failMsg", "没有权限访问");
			return "/fail";
		}		
	}

	/**
	 * 给界面设置项目名称
	 * @param user
	 * @param model
	 */
	private void setProjectName(User user,Model model)
	{
		String projectName = "";
		Project project = projectDao.selectByPrimaryKey(user.getProjectId());
		if(project != null)
		{
			projectName = project.getName();
		}
		model.addAttribute("projectName", projectName);
	}
	
	/**
	 * 获取登录用户ID
	 * 
	 * @param httpSession
	 * @return
	 */
	private String getSessionUserId(HttpSession httpSession)
	{
		if (httpSession == null)
		{
			logger.error("用户未认证！");
			return "123";
		}
		else
		{
			return httpSession.getAttribute("userId").toString();
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ProjectListDto getMainMangerProjectList(Model model, HttpSession httpSession)
	{
//		List<ProjectListDto> list = new ArrayList<ProjectListDto>();
//		ProjectListDto dto = new ProjectListDto();
//		dto.setProjectName("项目1");
//		dto.setFileName("xxx");
//		list.add(dto);
//		dto = new ProjectListDto();
//		dto.setProjectName("项目2");
//		dto.setFileName("yyy");
//		list.add(dto);
//		return dto;
		return null;
	}
	
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public String test2(Model model,HttpServletRequest request, HttpSession httpSession)	
	{
		User dbuser = (User) httpSession.getAttribute("currentUser");
		model.addAttribute("failMsg", dbuser.getUserName() + "_" + dbuser.getPassword());
		return "/fail";
	}
}
