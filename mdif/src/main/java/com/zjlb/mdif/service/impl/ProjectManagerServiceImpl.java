package com.zjlb.mdif.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartFile;

import com.zjlb.mdif.dao.TemplateDao;
import com.zjlb.mdif.dao.UploadFileDao;
import com.zjlb.mdif.dao.UserDao;
import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.Template;
import com.zjlb.mdif.entity.UploadFile;
import com.zjlb.mdif.entity.User;
import com.zjlb.mdif.entity.UtilConstants;
import com.zjlb.mdif.service.ProjectManagerService;

/**
 * 项目管理员对应的业务逻辑
 * @author Administrator
 *
 */
@Service
@EnableTransactionManagement
public class ProjectManagerServiceImpl implements ProjectManagerService
{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UploadFileDao uploadFileDao;
	
	@Autowired
	private TemplateDao templateDao;

	/**
     * 项目管理员获取当前项目的上报信息列表
     * @param userId
     * @return
     */
	@Override
	public List<ProjectListDto> selectProject(String userId)
	{		
		User user = userDao.selectByPrimaryKey(userId);		
		//判断是否为项目管理员
		if(user != null && isProjectManager(user))
		{
			return userDao.selectProject(user.getProjectId());
		}
		else
		{
			return null;
		}
		
	}

	/**
     * 获取UserId对应的项目管理员的所有操作员信息
     * @param userId
     * @return
     */
	@Override
	public List<User> selectProjectUsers(String userId)
	{
		User user = userDao.selectByPrimaryKey(userId);		
		//判断是否为项目管理员
		if(user != null && isProjectManager(user))
		{
			return userDao.selectOperatorByProjectId(user.getProjectId());
		}
		else
		{
			return null;
		}
		
	}

	/**
	 * 判断用户是否为项目管理员
	 * @param user
	 * @return
	 */
	private boolean isProjectManager(User user)
	{
		return user.getRole() == (byte)1;
	}
	
	/**
	 * 判断用户是否为区域操作员
	 * @param user
	 * @return
	 */
	private boolean isRegionOperator(User user)
	{
		return user.getRole() == (byte)2;
	}
	
	/**
	 *  删除操作员
	 */
	@Override
	public boolean deleteUser(String userId,String sessionUserId)
	{
		User user = userDao.selectByPrimaryKey(sessionUserId);
		if(user != null && isProjectManager(user))
		{
			user = userDao.selectByPrimaryKey(userId);
			if(user != null)
			{
				userDao.deleteByPrimaryKey(userId);
				return true;
			}
		}
		return false;
	}

	/**
     * 添加操作员
     * @param user
     * @param sessionUserId
     * @return
	 * @throws Exception 
     */
	@Override
	public boolean addUser(User user, String sessionUserId) throws Exception 
	{
		User manager = userDao.selectByPrimaryKey(sessionUserId);
		//项目管理员才有权限添加
		if(manager != null && isProjectManager(manager))
		{			
			User oldUser = userDao.selectByUserName(user.getUserName());
			if(oldUser == null || StringUtils.isEmpty(oldUser.getUserId()))
			{
				//不存在该用户
				user.setUserId(UUID.randomUUID().toString());
				user.setProjectId(manager.getProjectId());
				user.setRole((byte)2);//操作员
				userDao.insert(user);
				return true;
			}
			else
			{
				throw new Exception(UtilConstants.USER_NAME_REPEAT);
			}
		}
		return false;
	}

	/**
     * 上传申报数据
     * @param sourceFile 区域操作员上传的申报数据
     * @param userId 当前用户
     * @return 成功或失败
	 * @throws IOException 
	 * @throws IllegalStateException 
     */
	@Override
	public boolean uploadFiles(MultipartFile sourceFile, String userId,String month) throws IllegalStateException, IOException
	{	
		User user = userDao.selectByPrimaryKey(userId);
		//只有区域操作员才有权限上传申报数据
		if(user != null && isRegionOperator(user))
		{			
			//获取上传目标路径
			String fileId = UUID.randomUUID().toString();
			String fileName = sourceFile.getOriginalFilename();
			File file = getTargetUploadFile(fileId,fileName);
			
			//将文件存放到指定路径
			sourceFile.transferTo(file);
			
			//写入数据库			
			uploadFileDao.insert(newUploadFile(fileId,fileName,month,user));
			return true;
			
		}
		else
		{
			return false;
		}
		
	}
	
	/**
	 * 新建一个上传数据对象
	 * @param fileId
	 * @param fileName
	 * @param month
	 * @param user
	 * @return
	 */
	private UploadFile newUploadFile(String fileId,String fileName,String month,User user)
	{
		UploadFile uploadFile = new UploadFile();
		uploadFile.setUploadId(UUID.randomUUID().toString());
		uploadFile.setFileId(fileId);
		uploadFile.setFileName(fileName);
		uploadFile.setMonth(month);
		uploadFile.setProjectId(user.getProjectId());
		uploadFile.setUserId(user.getUserId());
		return uploadFile;
	}
	
	/**
	 * 获取操作员上传的目标文件
	 * @param fileId 保存在数据库中的文件ID
	 * @param fileName 原来的文件名包括后缀名
	 * @return
	 */
	private File getTargetUploadFile(String fileId,String fileName)
	{
		String prefix = fileName.substring(fileName.lastIndexOf("."));
		String targetPath = getFilesPath(); 
		return new File(targetPath + File.separator + "files" + File.separator +"upload",fileId + prefix);
	}
	
	/**
	 * 获取上传模板的目标路径
	 * @param fileId
	 * @param fileName
	 * @return
	 */
	private File getTargetUploadTemplateFile(String fileId,String fileName)
	{
		String prefix = fileName.substring(fileName.lastIndexOf("."));
		String targetPath = getFilesPath(); 
		return new File(targetPath + File.separator + "files" + File.separator +"template",fileId + prefix);
	}
	
	
	/**
	 * 获取上传文件保存路径
	 * @return
	 */
	private String getFilesPath()
	{
		File f = new File(this.getClass().getResource("/").getPath());
		return f.getParentFile().getParentFile().getParentFile().getParentFile().getParent();
	}

	@Override
	public boolean deleteUploadFile(String uploadId, String userId)
	{
		User user = userDao.selectByPrimaryKey(userId);
		//只有区域操作员才有权限删除上传文件
		if(user != null && isRegionOperator(user))
		{		
			UploadFile uploadFile = uploadFileDao.selectByPrimaryKey(uploadId);
			if(uploadFile != null && uploadFile.getUserId().equals(user.getUserId()))
			{
				uploadFileDao.deleteByPrimaryKey(uploadId);
				File file = getTargetUploadFile(uploadFile.getFileId(),uploadFile.getFileName());
				if(file != null && file.exists())
				{
					file.delete();
				}
				return true;
			}
			else
			{
				return false;
			}			
		}
		else
		{
			return false;
		}
	}
	
	

	@Override
	public boolean uploadTemplate(MultipartFile templateFile, String userId)
			throws IllegalStateException, IOException 
	{
		User user = userDao.selectByPrimaryKey(userId);
		//只有项目管理员才有权限上传模板文件
		if(user != null && isProjectManager(user))
		{			
			//获取上传目标路径
			String fileId = UUID.randomUUID().toString();
			String fileName = templateFile.getOriginalFilename();
			File file = getTargetUploadTemplateFile(fileId,fileName);
			
			//将文件存放到指定路径
			templateFile.transferTo(file);
			
			//写入数据库			
			templateDao.insert(newTemplate(fileId,fileName,user));
			return true;
			
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 新建一个模板文件
	 * @param fileId 将fileId作为Template的主键id进行管理
	 * @param fileName
	 * @param user
	 * @return
	 */
	private Template newTemplate(String fileId,String fileName,User user)
	{
		Template template = new Template();
		template.setTemplateId(fileId);
		template.setName(fileName);
		template.setProjectId(user.getProjectId());		
		return template;
	}

	/**
	 * 删除模板文件
	 */
	@Override
	public boolean deleteTemplateFile(String templateId, String userId) 
	{
		User user = userDao.selectByPrimaryKey(userId);
		//只有项目管理员才有权限上传模板文件
		if(user != null && isProjectManager(user))
		{		
			Template template = templateDao.selectByPrimaryKey(templateId);
			if(template != null && template.getProjectId().equals(user.getProjectId()))
			{
				templateDao.deleteByPrimaryKey(templateId);
				File file = getTargetUploadTemplateFile(template.getTemplateId(),template.getName());
				if(file != null && file.exists())
				{
					file.delete();
				}
				return true;
			}
			else
			{
				return false;
			}			
		}
		else
		{
			return false;
		}
	}

	@Override
	public String GetFileName(String uploadId)
	{
		UploadFile uploadFile = uploadFileDao.selectByPrimaryKey(uploadId);
		if(uploadFile != null && !StringUtils.isEmpty(uploadFile.getFileId()))
		{
			return uploadFile.getFileName();			
		}
		else
		{
			return null;
		}		
	}
	
	
    /**
     * 获取申报数据的文件
     */
	@Override
	public File GetFile(String uploadId)
	{
		UploadFile uploadFile = uploadFileDao.selectByPrimaryKey(uploadId);
		if(uploadFile != null && !StringUtils.isEmpty(uploadFile.getFileId()))
		{
			return getTargetUploadFile(uploadFile.getFileId(),uploadFile.getFileName());			
		}
		else
		{
			return null;
		}		
	}

	@Override
	public String GetTempateFileName(String templateId)
	{
		Template template = templateDao.selectByPrimaryKey(templateId);
		if (template != null) {
			return template.getName();
		} else {
			return null;
		}
		
	}

	@Override
	public File GetTemplateFile(String templateId)
	{
		Template template = templateDao.selectByPrimaryKey(templateId);
		if(template != null )
		{
			return getTargetUploadTemplateFile(template.getTemplateId(),template.getName());		
		}
		else
		{
			return null;
		}	
	}
	
	

	
}
