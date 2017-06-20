package com.zjlb.mdif.controller.rest;

import java.io.File;

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


@RestController
@RequestMapping("/project")
public class RestProjectController
{
	private final Logger logger = LoggerFactory.getLogger(RestProjectController.class);
	
	@Autowired
	private MainManagerService mainManagerService;
	
	@Autowired
	private ProjectManagerService projectManagerService;
	
	/**
	 * �ܹ���Ա��ȡ������Ŀ�б�
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getMainManagerProjectList", method = RequestMethod.GET)  
    public ResultDto<ProjectListDto> getMainManagerProjectList(Model model) 
	{  
		ResultDto<ProjectListDto> result = new ResultDto<ProjectListDto>();	
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(mainManagerService.selectAllProjects());
		}
		catch(Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getMainManagerProjectList: " + ex.getMessage());
		}		
        return result;  
    } 
	
	
	@RequestMapping(value = "/searchMainManagerProjectList", method = RequestMethod.GET)  
    public ResultDto<ProjectListDto> searchMainManagerProjectList(@PathVariable String name) 
	{  
		ResultDto<ProjectListDto> result = new ResultDto<ProjectListDto>();	
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(mainManagerService.selectAllProjects());
		}
		catch(Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getMainManagerProjectList: " + ex.getMessage());
		}		
        return result;  
    } 
	
	/**
	 * ��ȡ����Ա��Ӧ��Ŀ�������б���Ϣ
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
		catch(Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getManagerProjectList: " + ex.getMessage());
		}		
        return result;  
    } 
	
	/**
	 * ��ȡ����Ա��Ӧ�Ĳ���Ա��Ϣ
	 * @return
	 */
	@RequestMapping(value = "/getManagerUsers", method = RequestMethod.GET)  
    public ResultDto<User> getManagerUserList(HttpSession httpSession) 
	{  
		ResultDto<User> result = new ResultDto<User>();	
		try
		{
			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
			result.setMessageCode(UtilConstants.SUCCESS_CODE);
			result.setResult(projectManagerService.selectProjectUsers(getSessionUserId(httpSession)));
		}
		catch(Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getManagerUserList: " + ex.getMessage());
		}		
        return result;  
    } 
	
	
	/**
	 * ��Ŀ����Ա���Ӳ���Ա
	 * @return
	 */
	@RequestMapping(value = "/addManagerUsers")  
    public ResultDto<String> addManagerUser(@RequestBody User user, HttpSession httpSession) 
	{  
		ResultDto<String> result = new ResultDto<String>();	
		try
		{
			if(user != null)
			{
				if(checkUser(user))
				{
					if(projectManagerService.addUser(user, getSessionUserId(httpSession)))
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
				else
				{
					result.setMessage(UtilConstants.USER_NAME_UNCOMPLETED);
					result.setMessageCode(UtilConstants.ERROR_CODE);
				}				
			}
			else
			{
				result.setMessage(UtilConstants.ERROR_EMPTY);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}
			
		}
		catch(Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("getManagerUserList: " + ex.getMessage());
		}		
        return result;  
    } 
	
	/**
	 * ���û���Ϣ����Ҫ�ļ��
	 * ����Ҫ8λ������
	 * @param user
	 * @return
	 */
	private boolean checkUser(User user)
	{
		if(StringUtils.isEmpty(user.getUserName()))
		{
			return false;
		}
		if(StringUtils.isEmpty(user.getPassword()) || user.getPassword().length() < UtilConstants.PASSWORD_LEN)
		{
			return false;
		}
		if(StringUtils.isEmpty(user.getHospital()))
		{
			return false;
		}
		if(StringUtils.isEmpty(user.getRegion()))
		{
			return false;
		}
		return true;
		
	}

	/**
	 * ��ȡ��¼�û�ID
	 * @param httpSession
	 * @return
	 */
	private String getSessionUserId(HttpSession httpSession)
	{
		return httpSession.getAttribute("userId").toString();
	}
	
	/**
	 * ����Ա�ϴ��걨��Ϣ 
	 * @param sourceFile
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/uploadFiles.ajax")
	@ResponseBody
    public ResultDto<String> uploadFiles(@RequestParam(value = "uploadFile") MultipartFile sourceFile,String month, HttpSession httpSession) 
	{  
		ResultDto<String> result = new ResultDto<String>();	
		try
		{
			if(projectManagerService.uploadFiles(sourceFile, getSessionUserId(httpSession), month))
			{
				result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
				result.setMessageCode(UtilConstants.SUCCESS_CODE);	
			}
			else
			{
				result.setMessage(UtilConstants.MESSAGE_FAILURE);
				result.setMessageCode(UtilConstants.ERROR_CODE);
			}
			
			
//			String fileName = sourceFile.getOriginalFilename();
//			//��ȡ�ϴ�·��
//			File f = new File(this.getClass().getResource("/").getPath());
//			String targetPath = f.getParentFile().getParentFile().getParentFile().getParentFile().getParent();
//			System.out.println(targetPath);
//			File file = new File(targetPath + File.separator + "file" + File.separator +"upload",fileName);
//			sourceFile.transferTo(file);
//			result.setMessage(UtilConstants.MESSAGE_SUCCESSFUL);
//			result.setMessageCode(UtilConstants.SUCCESS_CODE);	
		}
		catch(Exception ex)
		{			
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("uploadFiles: " + ex.getMessage());
		}		
        return result;  
    } 
	
	/**
	 * �ϴ�ģ���ļ�
	 * @param sourceFile
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/uploadTemplate.ajax")
	@ResponseBody
    public ResultDto<String> uploadTemplate(@RequestParam(value = "uploadTemplate") MultipartFile templateFile, HttpSession httpSession) 
	{  
		ResultDto<String> result = new ResultDto<String>();	
		try
		{
			if(projectManagerService.uploadTemplate(templateFile, getSessionUserId(httpSession)))
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
		catch(Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("uploadTemplate: " + ex.getMessage());
		}		
        return result;  
    } 
	
	/**
	 * ɾ�����ϴ��ļ�
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
			if(projectManagerService.deleteUploadFile(uploadId, getSessionUserId(httpSession)))
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
		catch(Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("deleteUploadFile: " + ex.getMessage());
		}		
        return result;  
    }
	
	/**
	 * ɾ��ģ���ļ�
	 * @param sourceFile
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/deleteTemplate.ajax")
	@ResponseBody
    public ResultDto<String> deleteTemplate(String templateId, HttpSession httpSession) 
	{  
		ResultDto<String> result = new ResultDto<String>();	
		try
		{
			if(projectManagerService.deleteTemplateFile(templateId, getSessionUserId(httpSession)))
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
		catch(Exception ex)
		{
			result.setMessage(UtilConstants.MESSAGE_FAILURE);
			result.setMessageCode(UtilConstants.ERROR_CODE);
			logger.debug("deleteTemplate: " + ex.getMessage());
		}		
        return result;  
    }
	
}