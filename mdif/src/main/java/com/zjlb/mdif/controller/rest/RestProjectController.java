package com.zjlb.mdif.controller.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zjlb.mdif.dao.HospitalDao;
import com.zjlb.mdif.dao.RegionDao;
import com.zjlb.mdif.entity.CommonEnum.UploadStatus;
import com.zjlb.mdif.entity.Hospital;
import com.zjlb.mdif.entity.MyDownloadFileDtoList;
import com.zjlb.mdif.entity.MyUserDto;
import com.zjlb.mdif.entity.MyUserListDto;
import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.Region;
import com.zjlb.mdif.entity.ResultDto;
import com.zjlb.mdif.entity.ResultSingleDto;
import com.zjlb.mdif.entity.Template;
import com.zjlb.mdif.entity.TemplateDto;
import com.zjlb.mdif.entity.TemplateDtoList;
import com.zjlb.mdif.entity.User;
import com.zjlb.mdif.entity.UtilConstants;
import com.zjlb.mdif.service.MainManagerService;
import com.zjlb.mdif.service.ProjectManagerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/project")
public class RestProjectController
{
	private final Logger logger = LoggerFactory.getLogger(RestProjectController.class);

	@Autowired
	private MainManagerService mainManagerService;

	@Autowired
	private ProjectManagerService projectManagerService;
	
	@Autowired
	private HospitalDao hospitalDao;
	
	@Autowired
	private RegionDao regionDao;

	@RequestMapping(value = "/getHospitals.ajax", method = RequestMethod.GET)
	public ResultDto<Hospital> getHospitals(Model model, HttpSession httpSession)
	{
		ResultDto<Hospital> result = new ResultDto<Hospital>();
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(hospitalDao.selectAll());
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getMainManagerProjectList: " + ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/getRegion.ajax", method = RequestMethod.GET)
	public ResultDto<Region> getRegion(Model model, HttpSession httpSession)
	{
		ResultDto<Region> result = new ResultDto<Region>();
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(regionDao.selectAll());
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getMainManagerProjectList: " + ex.getMessage());
		}
		return result;
	}
	
	/**
	 * 总管理员获取所有项目列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getMainManagerProjectList.ajax", method = RequestMethod.GET)
	public ResultDto<ProjectListDto> getMainManagerProjectList(Model model, HttpSession httpSession)
	{
		ResultDto<ProjectListDto> result = new ResultDto<ProjectListDto>();
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(mainManagerService.selectAllProjects(getSessionUser(httpSession), UploadStatus.NON));
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getMainManagerProjectList: " + ex.getMessage());
		}
		return result;
	}

	/**
	 * 总管理员获取所有项目列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getMainManagerUploadedProjectList.ajax", method = RequestMethod.GET)
	public ResultDto<ProjectListDto> getMainManagerUploadedProjectList(Model model, HttpSession httpSession)
	{
		ResultDto<ProjectListDto> result = new ResultDto<ProjectListDto>();
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(mainManagerService.selectAllProjects(getSessionUser(httpSession), UploadStatus.UPLOADED));
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getMainManagerProjectList: " + ex.getMessage());
		}
		return result;
	}
	
	/**
	 * 总管理员获取所有项目列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getMainManagerUnUploadedProjectList.ajax", method = RequestMethod.GET)
	public ResultDto<ProjectListDto> getMainManagerUnUploadedProjectList(Model model, HttpSession httpSession)
	{
		ResultDto<ProjectListDto> result = new ResultDto<ProjectListDto>();
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(mainManagerService.selectAllProjects(getSessionUser(httpSession), UploadStatus.UNUPLOAD));
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getMainManagerProjectList: " + ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/searchMainManagerProjectList", method = RequestMethod.GET)
	public ResultDto<ProjectListDto> searchMainManagerProjectList(@PathVariable String name, HttpSession httpSession)
	{
		ResultDto<ProjectListDto> result = new ResultDto<ProjectListDto>();
		try
		{
			if (!StringUtils.isEmpty(name))
			{
				result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
				result.setMessageCode(UtilConstants.SUCCESS_CODE);
				result.setResult(mainManagerService.searchProjects(name, getSessionUser(httpSession)));
			}
			else
			{
				result.setMessage(UtilConstants.MESSAGE_FAILURE);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}

		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getMainManagerProjectList: " + ex.getMessage());
		}
		return result;
	}

	/**
	 * 获取管理员对应项目的下载列表信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getManagerProjectList", method = RequestMethod.GET)
	public ResultDto<ProjectListDto> getManagerProjectList(HttpSession httpSession)
	{
		ResultDto<ProjectListDto> result = new ResultDto<ProjectListDto>();
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(projectManagerService.selectProject(getSessionUserId(httpSession)));
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getManagerProjectList: " + ex.getMessage());
		}
		return result;
	}

	/**
	 * 获取管理员对应的操作员信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getManagerUsers.ajax", method = RequestMethod.GET)
	public ResultSingleDto<MyUserListDto> getManagerUserList(HttpSession httpSession)
	{
		ResultSingleDto<MyUserListDto> result = new ResultSingleDto<MyUserListDto>();
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			List<User> users = projectManagerService.selectProjectUsers(getSessionUserId(httpSession));
			result.setResult(convertDto(users));
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getManagerUserList: " + ex.getMessage());
		}
		return result;
	}
	
	
	private MyUserListDto convertDto(List<User> users)
	{
		MyUserListDto dto = new MyUserListDto();
		List<MyUserDto> myUsers = new ArrayList<MyUserDto>();
		if(users != null && users.size() >0)
		{
			dto.setTotal(users.size());			
			for(User user : users)
			{
				myUsers.add(getMyUserDto(user));
			}			
		}
		dto.setRows(myUsers);
		return dto;		
	}
	
	private MyUserDto getMyUserDto(User user)
	{
		MyUserDto dto = new MyUserDto();
		dto.setId(user.getUserId());
		dto.setHospital(user.getHospital());
		dto.setPassword(user.getPassword());
		dto.setRegion(user.getRegion());
		dto.setUserName(user.getUserName());
		//dto.setOperator("删除");
		return dto;
		
	}

	/**
	 * 项目管理员添加操作员
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addManagerUsers.ajax", method = RequestMethod.POST)
	public ResultDto<String> addManagerUser(String regionName,String hospitalName,String userName,String password,String passwordConfirm, HttpSession httpSession)
	{
		ResultDto<String> result = new ResultDto<String>();
		try
		{
			if(StringUtils.isEmpty(regionName) || StringUtils.isEmpty(regionName) || StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(passwordConfirm))
			{
				result.setMessage(UtilConstants.USER_NAME_UNCOMPLETED);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}
			else
			{
				if(!password.equals(passwordConfirm))
				{
					result.setMessage(UtilConstants.MESSAGE_PASSWORD_CONFIRM);
					result.setMessageCode(UtilConstants.ERROR_CODE);
				}
				else
				{
					User user = getNewNormalUser(regionName,hospitalName,userName,password);
					if (projectManagerService.addUser(user, getSessionUserId(httpSession)))
					{
						result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
						result.setMessageCode(UtilConstants.SUCCESS_CODE);
					}
					else
					{
						result.setMessage(UtilConstants.MESSAGE_FAILURE);
						result.setMessageCode(UtilConstants.ERROR_CODE);
					}
				}
			}
		}
		catch (Exception ex)
		{
			result.setMessage(ex.getMessage());
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getManagerUserList: " + ex.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 项目管理员添加操作员
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteUser.ajax", method = RequestMethod.POST)
	public ResultDto<String> deleteUser(String id, HttpSession httpSession)
	{
		ResultDto<String> result = new ResultDto<String>();
		try
		{
			if(StringUtils.isEmpty(id))
			{
				result.setMessage(UtilConstants.ERROR_EMPTY);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}
			else
			{
				if (projectManagerService.deleteUser(id, getSessionUserId(httpSession)))
				{
					result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
					result.setMessageCode(UtilConstants.SUCCESS_CODE);
				}
				else
				{
					result.setMessage(UtilConstants.MESSAGE_FAILURE);
					result.setMessageCode(UtilConstants.ERROR_CODE);
				}
			}
		}
		catch (Exception ex)
		{
			result.setMessage(ex.getMessage());
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("deleteUser: " + ex.getMessage());
		}
		return result;
	}
	
	private User getNewNormalUser(String regionName,String hospitalName,String userName,String password)
	{
		User user = new User();
		user.setRegion(regionName);
		user.setHospital(hospitalName);
		user.setUserName(userName);
		user.setPassword(password);
		return user;
	}

	/**
	 * 对用户信息作必要的检查 密码要8位或以上
	 * 
	 * @param user
	 * @return
	 */
	private boolean checkUser(User user)
	{
		if (StringUtils.isEmpty(user.getUserName()))
		{
			return false;
		}
		if (StringUtils.isEmpty(user.getPassword()) || user.getPassword().length() < UtilConstants.PASSWORD_LEN)
		{
			return false;
		}
		if (StringUtils.isEmpty(user.getHospital()))
		{
			return false;
		}
		if (StringUtils.isEmpty(user.getRegion()))
		{
			return false;
		}
		return true;

	}

	/**
	 * 获取登录用户ID
	 * 
	 * @param httpSession
	 * @return
	 */
	private String getSessionUserId(HttpSession httpSession)
	{
		User user = getSessionUser(httpSession);
		if(user != null)
		{
			return user.getUserId();
		}
		else
		{
			return null;
		}
	}

	/**
	 * 获取登录用户ID
	 * 
	 * @param httpSession
	 * @return
	 */
	private User getSessionUser(HttpSession httpSession)
	{
		return (User) httpSession.getAttribute("currentUser");
	}
	
	
	/**
	 * 操作员上传申报信息
	 * 
	 * @param sourceFile
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/uploadFiles.ajax")
	@ResponseBody
	public ResultDto<String> uploadFiles(@RequestParam(value = "sourceFile") MultipartFile sourceFile, String monthText,
			HttpSession httpSession)
	{
		ResultDto<String> result = new ResultDto<String>();
		try
		{
			if (projectManagerService.uploadFiles(sourceFile, getSessionUserId(httpSession), monthText))
			{
				result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
				result.setMessageCode(UtilConstants.SUCCESS_CODE);
			}
			else
			{
				result.setMessage(UtilConstants.MESSAGE_FAILURE);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}

			// String fileName = sourceFile.getOriginalFilename();
			// //获取上传路径
			// File f = new File(this.getClass().getResource("/").getPath());
			// String targetPath =
			// f.getParentFile().getParentFile().getParentFile().getParentFile().getParent();
			// System.out.println(targetPath);
			// File file = new File(targetPath + File.separator + "file" +
			// File.separator +"upload",fileName);
			// sourceFile.transferTo(file);
			// result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			// result.setMessageCode(UtilConstants.SUCCESS_CODE);
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("uploadFiles: " + ex.getMessage());
		}
		return result;
	}

//	private String getMonthText(String monthText)
//	{
//		return monthText.replace("/", "年") + "月";
//	}
	
	
	/**
	 * 上传模板文件
	 * 
	 * @param sourceFile
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/uploadTemplate.ajax")
	@ResponseBody
	public ResultDto<String> uploadTemplate(@RequestParam(value = "sourceFile") MultipartFile templateFile,
			HttpSession httpSession)
	{
		ResultDto<String> result = new ResultDto<String>();
		try
		{
			if (projectManagerService.uploadTemplate(templateFile, getSessionUserId(httpSession)))
			{
				result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
				result.setMessageCode(UtilConstants.SUCCESS_CODE);
			}
			else
			{
				result.setMessage(UtilConstants.MESSAGE_FAILURE);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("uploadTemplate: " + ex.getMessage());
		}
		return result;
	}

	/**
	 * 删除已上传文件
	 * 
	 * @param sourceFile
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/deleteUploadFile.ajax")
	@ResponseBody
	public ResultDto<String> deleteUploadFile(String uploadId, HttpSession httpSession)
	{
		ResultDto<String> result = new ResultDto<String>();
		try
		{
			if (projectManagerService.deleteUploadFile(uploadId, getSessionUserId(httpSession)))
			{
				result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
				result.setMessageCode(UtilConstants.SUCCESS_CODE);
			}
			else
			{
				result.setMessage(UtilConstants.MESSAGE_FAILURE);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("deleteUploadFile: " + ex.getMessage());
		}
		return result;
	}

	/**
	 * 删除模板文件
	 * 
	 * @param sourceFile
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/deleteTemplate.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ResultDto<String> deleteTemplate(String templateId, HttpSession httpSession)
	{
		ResultDto<String> result = new ResultDto<String>();
		try
		{
			if (projectManagerService.deleteTemplateFile(templateId, getSessionUserId(httpSession)))
			{
				result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
				result.setMessageCode(UtilConstants.SUCCESS_CODE);
			}
			else
			{
				result.setMessage(UtilConstants.MESSAGE_FAILURE);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("deleteTemplate: " + ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/downloadFile.ajax")
	public String downloadFile(String uploadId, HttpServletRequest request, HttpServletResponse response)
	{
		if (!StringUtils.isEmpty(uploadId))
		{
			String fileName = projectManagerService.GetFileName(uploadId);
			if (!StringUtils.isEmpty(fileName))
			{
				File file = projectManagerService.GetFile(uploadId);
				downloadFile(file, fileName, response);
			}
		}
		return null;
	}

	/**
	 * 下载文件
	 * 
	 * @param file
	 * @param fileName
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	private void downloadFile(File file, String fileName, HttpServletResponse response)
	{
		try
		{
			if (file.exists())
			{
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.setContentType("application/json;charset=UTF-8");
				String encodeFileName = new String(fileName.getBytes("GB2312"),"iso8859-1");
				response.addHeader("Content-Disposition", "attachment;fileName=" + encodeFileName);// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try
				{
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1)
					{
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
					os.close();
				}
				catch (Exception e)
				{
					// TODO: handle exception
					e.printStackTrace();
				}
				finally
				{
					if (bis != null)
					{
						try
						{
							bis.close();
						}
						catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (fis != null)
					{
						try
						{
							fis.close();
						}
						catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		catch(Exception ex)
		{
			
		}
		
	}

	@RequestMapping("/downloadTemplateFile.ajax")
	public String downloadTemplateFile(String templateId, HttpServletRequest request, HttpServletResponse response)
	{
		if (!StringUtils.isEmpty(templateId))
		{
			String fileName = projectManagerService.GetTempateFileName(templateId);
			if (!StringUtils.isEmpty(fileName))
			{
				File file = projectManagerService.GetTemplateFile(templateId);
				downloadFile(file, fileName, response);
			}
		}
		return null;
	}
	
	/**
	 * 获取项目对应的模板
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/getTemplateList.ajax", method = RequestMethod.GET)
	public ResultSingleDto<TemplateDtoList> getTemplateList(HttpSession httpSession)
	{
		ResultSingleDto<TemplateDtoList> result = new ResultSingleDto<TemplateDtoList>();
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			List<Template> templates = projectManagerService.GetMyTemplateList(getSessionUser(httpSession));
			result.setResult(convertTemplateDto(templates));
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getTemplateList: " + ex.getMessage());
		}
		return result;
	}
	
	private TemplateDtoList convertTemplateDto(List<Template> templates)
	{
		TemplateDtoList dto = new TemplateDtoList();
		List<TemplateDto> myTemplates = new ArrayList<TemplateDto>();
		if(templates != null && templates.size() >0)
		{
			dto.setTotal(templates.size());			
			for(Template template : templates)
			{
				myTemplates.add(getTemplateDto(template));
			}			
		}		
		dto.setRows(myTemplates);
		return dto;		
	}
	
	private TemplateDto getTemplateDto(Template template)
	{
		TemplateDto dto = new TemplateDto();		
		dto.setId(template.getTemplateId());
		dto.setTemplateName(template.getName());		
		return dto;
		
	}
	
	
	@RequestMapping(value = "/getMyDownloadList.ajax", method = RequestMethod.GET)
	public ResultSingleDto<MyDownloadFileDtoList> getMyDownloadList(HttpSession httpSession)
	{
		ResultSingleDto<MyDownloadFileDtoList> result = new ResultSingleDto<MyDownloadFileDtoList>();
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(projectManagerService.getMyDownloadFiles(this.getSessionUser(httpSession)));
		}
		catch (Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getMyDownloadList: " + ex.getMessage());
		}
		return result;
	}
}
