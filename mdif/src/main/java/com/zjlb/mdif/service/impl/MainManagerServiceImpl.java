package com.zjlb.mdif.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ctc.wstx.util.StringUtil;
import com.zjlb.mdif.dao.ProjectDao;
import com.zjlb.mdif.dao.UploadFileDao;
import com.zjlb.mdif.dao.UserDao;
import com.zjlb.mdif.entity.Project;
import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.ProjectListWithPageDto;
import com.zjlb.mdif.entity.UploadFile;
import com.zjlb.mdif.entity.User;
import com.zjlb.mdif.entity.CommonEnum.UploadStatus;
import com.zjlb.mdif.entity.CommonEnum.UserType;
import com.zjlb.mdif.service.MainManagerService;
import com.zjlb.mdif.service.UserService;

/**
 * 总管理员对应的业务逻辑
 * @author Administrator
 *
 */
@Service
@EnableTransactionManagement
public class MainManagerServiceImpl implements MainManagerService
{
    @Autowired
    private UploadFileDao uploadFileDao;
    
    @Autowired
    private ProjectDao projectDao;
    
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 总管理员获取项目信息列表
	 */
	@Override
	public ProjectListWithPageDto selectAllProjects(User user,UploadStatus uploadStatus,int pageNumber,int pageSize)
	{
		List<ProjectListDto> projects = new ArrayList<ProjectListDto>();
		if(isAdminRole(user))
		{
			switch(uploadStatus)
			{
				case UPLOADED:
					projects = getUploadedProjects();
				case UNUPLOAD:
					projects =  getUnuploadedProjects();
				default:
					projects =  getAllProjects();
			}
		}
		//分页处理		
		return selectByPage(projects,pageNumber,pageSize);		
	}

	/**
	 * 获取已上传列表
	 * @return
	 */
	private List<ProjectListDto> getUploadedProjects()
	{
		List<ProjectListDto> uploadFiles = uploadFileDao.selectAllUploaded();
		if(uploadFiles != null && uploadFiles.size()>0)
		{
			User user;
			for(ProjectListDto item : uploadFiles)
			{
				user = userDao.selectProjectManager(item.getProjectId());
				if(user != null)
				{
					item.setProjectManager(user.getUserName());
				}
				item.setUploadStatus("是");
			}
		}
		return uploadFiles;
	}
	
	/**
	 * 获取所有项目列表
	 * @return
	 */
	private List<ProjectListDto> getAllProjects()
	{
		return getAllProjects( projectDao.selectAll());
	}
	
	private List<ProjectListDto> getUnuploadedProjects()
	{
		List<ProjectListDto> projectDtos = new ArrayList<ProjectListDto>();
		List<ProjectListDto> projectList = getAllProjects( projectDao.selectAll());
		if(projectList.size() >0)
		{
			for(ProjectListDto project : projectList)
			{
				if(project.getUploadStatus().equals("否"))
				{
					projectDtos.add(project);
				}
			}
		}
		return projectDtos;
	}
	
	
	private List<ProjectListDto> getAllProjects(List<Project> projects)
	{
		List<ProjectListDto> projectDtos = new ArrayList<ProjectListDto>();
		List<ProjectListDto> tempProjectDtos; 
		if(projects != null && projects.size()>0)
		{
			for(Project project : projects)
			{
				tempProjectDtos = getProjectListDto(project);
				if(tempProjectDtos != null && tempProjectDtos.size()>0)
				{
					projectDtos.addAll(tempProjectDtos);
				}
			}
		}		
		return projectDtos;
	}
	
	
	
	
	private List<ProjectListDto>  getProjectListDto(Project project)
	{
		List<ProjectListDto> projectList = new ArrayList<ProjectListDto>();
		User projectUser = userDao.selectProjectManager(project.getProjectId());
		List<User> users = userDao.selectOperatorByProjectId(project.getProjectId());
		List<UploadFile> uploadFiles = new ArrayList<UploadFile>();
		List<UploadFile> tempList;
		if(users != null && users.size()>0)
		{
			for(User user : users)
			{
				tempList = uploadFileDao.selectByUserId(user.getUserId());
				if(tempList != null && tempList.size()>0)
				{
					uploadFiles.addAll(tempList);
				}
			}
			//当前年月
			Calendar now = Calendar.getInstance();
			int nowYear = now.get(Calendar.YEAR);
			int nowMonth = now.get(Calendar.MONTH) + 1; 
		    for(int i = project.getStartYear();i<= nowYear;i++)
		    {
		    	for(int j = project.getStartMonth();j<= nowMonth;j++)
		    	{
		    		projectList.addAll(getProjectListDto(project,projectUser,i,j,users,uploadFiles));
		    	}
		    }
			return projectList;			
		}
		else
		{
			return null;
		}
		
	}
	
	private List<ProjectListDto>  getProjectListDto(Project project,String monthText)
	{
		List<ProjectListDto> projectList = new ArrayList<ProjectListDto>();
		User projectUser = userDao.selectProjectManager(project.getProjectId());
		List<User> users = userDao.selectOperatorByProjectId(project.getProjectId());
		List<UploadFile> uploadFiles = new ArrayList<UploadFile>();
		List<UploadFile> tempList;
		if(users != null && users.size()>0)
		{
			for(User user : users)
			{
				tempList = uploadFileDao.selectByUserId(user.getUserId());
				if(tempList != null && tempList.size()>0)
				{
					uploadFiles.addAll(tempList);
				}
			}
			//当前年月
			String[] monthArray = monthText.split("/");
			int nowYear = Integer.parseInt(monthArray[0].trim());
			int nowMonth = Integer.parseInt(monthArray[1].trim());
			projectList.addAll(getProjectListDto(project,projectUser,nowYear,nowMonth,users,uploadFiles));
			return projectList;			
		}
		else
		{
			return null;
		}
		
	}
	
	private List<ProjectListDto>  getProjectListDto(Project project,User projectUser,int yearValue,int monthValue,List<User> users,List<UploadFile> uploadFiles)
	{
		List<ProjectListDto> projectList = new ArrayList<ProjectListDto>();
		List<UploadFile> myUploadFiles = getUploadFiles(yearValue,monthValue,uploadFiles);
		for(User user : users)
		{
			projectList.add(getProjectDto(project,projectUser,yearValue,monthValue,user,uploadFiles));
		}
		return projectList;
	}
	
	private ProjectListDto getProjectDto(Project project,User projectUser,int yearValue,int monthValue,User user,List<UploadFile> uploadFiles)
	{
		ProjectListDto projectListDto = new ProjectListDto();
		UploadFile uploadFile = getMyUploadFile(user.getUserId(), uploadFiles,yearValue,monthValue);
		projectListDto.setHospital(user.getHospital());
		projectListDto.setMonthText(getMonthText(yearValue,monthValue));
		projectListDto.setProjectId(project.getProjectId());
		projectListDto.setProjectManager(projectUser.getUserName());
		projectListDto.setProjectName(project.getName());
		projectListDto.setRegion(user.getRegion());
		if(uploadFile != null)
		{
			projectListDto.setUploadStatus("是");
		}
		else
		{
			projectListDto.setUploadStatus("否");
		}		
		return projectListDto;
	}
	
	private String getMonthText(int yearValue,int monthValue)
	{
		return String.valueOf(yearValue) + "年" + String.valueOf(monthValue) + "月";
	}
	
	private UploadFile getMyUploadFile(String userId,List<UploadFile> uploadFiles,int yearValue,int monthValue)
	{
		if(uploadFiles != null && uploadFiles.size()>0)
		{
			for(UploadFile uploadFile : uploadFiles)
			{
				if(uploadFile.getUserId().equals(userId) && uploadFile.getMonthValue() == monthValue && uploadFile.getYearValue() == yearValue)
				{
					return uploadFile;
				}
			}
			return null;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * 获取指定月份的上传数据
	 * @param yearValue
	 * @param monthValue
	 * @param uploadFiles
	 * @return
	 */
	private List<UploadFile> getUploadFiles(int yearValue,int monthValue,List<UploadFile> uploadFiles)
	{
		List<UploadFile> myUploadFiles = new ArrayList<UploadFile>();
		if(uploadFiles != null && uploadFiles.size()>0)
		{
			for(UploadFile uploadFile : uploadFiles)
			{
				if(uploadFile.getYearValue() == yearValue && uploadFile.getMonthValue() == monthValue)
				{
					myUploadFiles.add(uploadFile);
				}
			}			
		}
		return myUploadFiles;
	}
	
	 /**
     * 总管理员根据项目名称查询项目信息列表
     * @param projectName
     * @return
     */
	@Override
	public List<ProjectListDto> searchProjects(String projectName,User user)
	{
		if(isAdminRole(user))
		{
			return getAllProjects( projectDao.selectByProjectName(projectName));
		}
		else
		{
			return null;
		}		
	}
	
	/**
	 * 判断是否为Admin角色
	 * @param user
	 * @return
	 */
	private boolean isAdminRole(User user)
	{
		return user != null && userService.getUserType(user) == UserType.MAIN_MANAGER;
	}

	@Override
	public ProjectListWithPageDto selectMyProject(User user, String monthText,int pageNumber,int pageSize)
	{		
		List<ProjectListDto> projectDtos = new ArrayList<ProjectListDto>();
		List<ProjectListDto> tempProjectDtos = null;
		
		if(StringUtils.isEmpty(monthText))
		{
			tempProjectDtos = getProjectListDto(projectDao.selectByPrimaryKey(user.getProjectId())); 
			
		}
		else
		{
			//按月份过滤
			tempProjectDtos = getProjectListDto(projectDao.selectByPrimaryKey(user.getProjectId()),monthText);
		}
		if(tempProjectDtos != null && tempProjectDtos.size()>0)
		{
			projectDtos.addAll(tempProjectDtos);
		}	
		//分页处理
		return selectByPage(projectDtos,pageNumber,pageSize);
	}
	
	
	private ProjectListWithPageDto selectByPage(List<ProjectListDto> projectDtos,int pageNumber,int pageSize)
	{
		ProjectListWithPageDto result = new ProjectListWithPageDto();
		List<ProjectListDto> projectLit = new ArrayList<ProjectListDto>();
		int startIndex = (pageNumber -1) * pageSize;
		int end = startIndex + pageSize;
		if(projectDtos.size() < end)
		{
			end = projectDtos.size();
		}
		if((startIndex < projectDtos.size()))
		{
			for(int i = startIndex; i<end; i++)
			{
				projectLit.add(projectDtos.get(i));
			}
		}	
		result.setRows(projectLit);
		result.setTotal(projectDtos.size());
		return result;
		
	}
	

}
