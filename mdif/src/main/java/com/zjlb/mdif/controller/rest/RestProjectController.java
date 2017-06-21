package com.zjlb.mdif.controller.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

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

import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.ResultDto;
import com.zjlb.mdif.entity.User;
import com.zjlb.mdif.entity.UtilConstants;
import com.zjlb.mdif.service.MainManagerService;
import com.zjlb.mdif.service.ProjectManagerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/project")
public class RestProjectController {
	private final Logger logger = LoggerFactory
			.getLogger(RestProjectController.class);

	@Autowired
	private MainManagerService mainManagerService;

	@Autowired
	private ProjectManagerService projectManagerService;

	/**
	 * 总管理员获取所有项目列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getMainManagerProjectList", method = RequestMethod.GET)
	public ResultDto<ProjectListDto> getMainManagerProjectList(Model model,
			HttpSession httpSession) {
		ResultDto<ProjectListDto> result = new ResultDto<ProjectListDto>();
		try {
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(mainManagerService
					.selectAllProjects(getSessionUserId(httpSession)));
		} catch (Exception ex) {
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getMainManagerProjectList: " + ex.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/searchMainManagerProjectList", method = RequestMethod.GET)
	public ResultDto<ProjectListDto> searchMainManagerProjectList(
			@PathVariable String name, HttpSession httpSession) {
		ResultDto<ProjectListDto> result = new ResultDto<ProjectListDto>();
		try {
			if (!StringUtils.isEmpty(name)) {
				result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
				result.setMessageCode(UtilConstants.SUCCESS_CODE);
				result.setResult(mainManagerService.searchProjects(name,
						getSessionUserId(httpSession)));
			} else {
				result.setMessage(UtilConstants.MESSAGE_FAILURE);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}

		} catch (Exception ex) {
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
	public ResultDto<ProjectListDto> getManagerProjectList(
			HttpSession httpSession) {
		ResultDto<ProjectListDto> result = new ResultDto<ProjectListDto>();
		try {
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(projectManagerService
					.selectProject(getSessionUserId(httpSession)));
		} catch (Exception ex) {
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
	@RequestMapping(value = "/getManagerUsers", method = RequestMethod.GET)
	public ResultDto<User> getManagerUserList(HttpSession httpSession) {
		ResultDto<User> result = new ResultDto<User>();
		try {
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(projectManagerService
					.selectProjectUsers(getSessionUserId(httpSession)));
		} catch (Exception ex) {
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
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
	@RequestMapping(value = "/addManagerUsers")
	public ResultDto<String> addManagerUser(@RequestBody User user,
			HttpSession httpSession) {
		ResultDto<String> result = new ResultDto<String>();
		try {
			if (user != null) {
				if (checkUser(user)) {
					if (projectManagerService.addUser(user,
							getSessionUserId(httpSession))) {
						result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
						result.setMessageCode(UtilConstants.SUCCESS_CODE);
					} else {
						result.setMessage(UtilConstants.MESSAGE_FAILURE);
						result.setMessageCode(UtilConstants.ERROR_CODE);
					}
				} else {
					result.setMessage(UtilConstants.USER_NAME_UNCOMPLETED);
					result.setMessageCode(UtilConstants.ERROR_CODE);
				}
			} else {
				result.setMessage(UtilConstants.ERROR_EMPTY);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}

		} catch (Exception ex) {
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getManagerUserList: " + ex.getMessage());
		}
		return result;
	}

	/**
	 * 对用户信息作必要的检查 密码要8位或以上
	 * 
	 * @param user
	 * @return
	 */
	private boolean checkUser(User user) {
		if (StringUtils.isEmpty(user.getUserName())) {
			return false;
		}
		if (StringUtils.isEmpty(user.getPassword())
				|| user.getPassword().length() < UtilConstants.PASSWORD_LEN) {
			return false;
		}
		if (StringUtils.isEmpty(user.getHospital())) {
			return false;
		}
		if (StringUtils.isEmpty(user.getRegion())) {
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
	private String getSessionUserId(HttpSession httpSession) {
		return httpSession.getAttribute("userId").toString();
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
	public ResultDto<String> uploadFiles(
			@RequestParam(value = "uploadFile") MultipartFile sourceFile,
			String month, HttpSession httpSession) {
		ResultDto<String> result = new ResultDto<String>();
		try {
			if (projectManagerService.uploadFiles(sourceFile,
					getSessionUserId(httpSession), month)) {
				result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
				result.setMessageCode(UtilConstants.SUCCESS_CODE);
			} else {
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
		} catch (Exception ex) {
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("uploadFiles: " + ex.getMessage());
		}
		return result;
	}

	/**
	 * 上传模板文件
	 * 
	 * @param sourceFile
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/uploadTemplate.ajax")
	@ResponseBody
	public ResultDto<String> uploadTemplate(
			@RequestParam(value = "uploadTemplate") MultipartFile templateFile,
			HttpSession httpSession) {
		ResultDto<String> result = new ResultDto<String>();
		try {
			if (projectManagerService.uploadTemplate(templateFile,
					getSessionUserId(httpSession))) {
				result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
				result.setMessageCode(UtilConstants.SUCCESS_CODE);
			} else {
				result.setMessage(UtilConstants.MESSAGE_FAILURE);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}
		} catch (Exception ex) {
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
	public ResultDto<String> deleteUploadFile(String uploadId,
			HttpSession httpSession) {
		ResultDto<String> result = new ResultDto<String>();
		try {
			if (projectManagerService.deleteUploadFile(uploadId,
					getSessionUserId(httpSession))) {
				result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
				result.setMessageCode(UtilConstants.SUCCESS_CODE);
			} else {
				result.setMessage(UtilConstants.MESSAGE_FAILURE);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}
		} catch (Exception ex) {
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
	@RequestMapping(value = "/deleteTemplate.ajax")
	@ResponseBody
	public ResultDto<String> deleteTemplate(String templateId,
			HttpSession httpSession) {
		ResultDto<String> result = new ResultDto<String>();
		try {
			if (projectManagerService.deleteTemplateFile(templateId,
					getSessionUserId(httpSession))) {
				result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
				result.setMessageCode(UtilConstants.SUCCESS_CODE);
			} else {
				result.setMessage(UtilConstants.MESSAGE_FAILURE);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}
		} catch (Exception ex) {
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("deleteTemplate: " + ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/downloadFile.ajax")
	public String downloadFile(String uploadId,
			HttpServletRequest request, HttpServletResponse response) {
		if (!StringUtils.isEmpty(uploadId)) 
		{
			String fileName = projectManagerService.GetFileName(uploadId);
			if(!StringUtils.isEmpty(fileName))
			{
				File file = projectManagerService.GetFile(uploadId);
				downloadFile(file,fileName,response);
			}
		}
		return null;
	}
	
	/**
	 * 下载文件
	 * @param file
	 * @param fileName
	 * @param response
	 */
	private void downloadFile(File file,String fileName,HttpServletResponse response)
	{
		if (file.exists()) {
			response.setContentType("application/force-download");// 设置强制下载不打开					
			response.addHeader("Content-Disposition",
					"attachment;fileName=" + fileName);// 设置文件名
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
				os.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@RequestMapping("/downloadTemplateFile.ajax")
	public String downloadTemplateFile(String templateId,
			HttpServletRequest request, HttpServletResponse response) {
		if (!StringUtils.isEmpty(templateId)) 
		{
			String fileName = projectManagerService.GetTempateFileName(templateId);
			if(!StringUtils.isEmpty(fileName))
			{
				File file = projectManagerService.GetTemplateFile(templateId);
				downloadFile(file,fileName,response);
			}
		}
		return null;
	}
}
