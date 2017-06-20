package com.zjlb.mdif.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.User;
import com.zjlb.mdif.service.UserService;


@Controller
public class UserController 
{
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping("/dologin.do") // url
	public String dologin(User user, Model model) 
	{
		logger.info("login ....");
		User dbuser = userService.doUserLogin(user);

		if (dbuser == null) {
			model.addAttribute("failMsg", "User does not exist or password error!");
			return "/jsp/fail";
		} else {
			model.addAttribute("successMsg", "login Succ!");
			model.addAttribute("name", user.getUserName());
			return "/jsp/success";
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)  
    public ProjectListDto getMainMangerProjectList(Model model) 
	{  
		List<ProjectListDto> list = new ArrayList<ProjectListDto>();
		ProjectListDto dto = new ProjectListDto();
		dto.setProjectName("项目1");
		dto.setFileName("xxx");
		list.add(dto);
		dto = new ProjectListDto();
		dto.setProjectName("项目2");
		dto.setFileName("yyy");
		list.add(dto);
        return dto;  
    }  
}
