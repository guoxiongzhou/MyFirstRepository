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
 * ��Ŀ����Ա��Ӧ��ҵ���߼�
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
     * ��Ŀ����Ա��ȡ��ǰ��Ŀ���ϱ���Ϣ�б�
     * @param userId
     * @return
     */
	@Override
	public List<ProjectListDto> selectProject(String userId)
	{		
		User user = userDao.selectByPrimaryKey(userId);		
		//�ж��Ƿ�Ϊ��Ŀ����Ա
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
     * ��ȡUserId��Ӧ����Ŀ����Ա�����в���Ա��Ϣ
     * @param userId
     * @return
     */
	@Override
	public List<User> selectProjectUsers(String userId)
	{
		User user = userDao.selectByPrimaryKey(userId);		
		//�ж��Ƿ�Ϊ��Ŀ����Ա
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
	 * �ж��û��Ƿ�Ϊ��Ŀ����Ա
	 * @param user
	 * @return
	 */
	private boolean isProjectManager(User user)
	{
		return user.getRole() == (byte)1;
	}
	
	/**
	 * �ж��û��Ƿ�Ϊ�������Ա
	 * @param user
	 * @return
	 */
	private boolean isRegionOperator(User user)
	{
		return user.getRole() == (byte)2;
	}
	
	/**
	 *  ɾ������Ա
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
     * ��Ӳ���Ա
     * @param user
     * @param sessionUserId
     * @return
	 * @throws Exception 
     */
	@Override
	public boolean addUser(User user, String sessionUserId) throws Exception 
	{
		User manager = userDao.selectByPrimaryKey(sessionUserId);
		//��Ŀ����Ա����Ȩ�����
		if(manager != null && isProjectManager(manager))
		{			
			User oldUser = userDao.selectByUserName(user.getUserName());
			if(oldUser == null || StringUtils.isEmpty(oldUser.getUserId()))
			{
				//�����ڸ��û�
				user.setUserId(UUID.randomUUID().toString());
				user.setProjectId(manager.getProjectId());
				user.setRole((byte)2);//����Ա
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
     * �ϴ��걨����
     * @param sourceFile �������Ա�ϴ����걨����
     * @param userId ��ǰ�û�
     * @return �ɹ���ʧ��
	 * @throws IOException 
	 * @throws IllegalStateException 
     */
	@Override
	public boolean uploadFiles(MultipartFile sourceFile, String userId,String month) throws IllegalStateException, IOException
	{	
		User user = userDao.selectByPrimaryKey(userId);
		//ֻ���������Ա����Ȩ���ϴ��걨����
		if(user != null && isRegionOperator(user))
		{			
			//��ȡ�ϴ�Ŀ��·��
			String fileId = UUID.randomUUID().toString();
			String fileName = sourceFile.getOriginalFilename();
			File file = getTargetUploadFile(fileId,fileName);
			
			//���ļ���ŵ�ָ��·��
			sourceFile.transferTo(file);
			
			//д�����ݿ�			
			uploadFileDao.insert(newUploadFile(fileId,fileName,month,user));
			return true;
			
		}
		else
		{
			return false;
		}
		
	}
	
	/**
	 * �½�һ���ϴ����ݶ���
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
	 * ��ȡ����Ա�ϴ���Ŀ���ļ�
	 * @param fileId ���������ݿ��е��ļ�ID
	 * @param fileName ԭ�����ļ���������׺��
	 * @return
	 */
	private File getTargetUploadFile(String fileId,String fileName)
	{
		String prefix = fileName.substring(fileName.lastIndexOf("."));
		String targetPath = getFilesPath(); 
		return new File(targetPath + File.separator + "files" + File.separator +"upload",fileId + prefix);
	}
	
	/**
	 * ��ȡ�ϴ�ģ���Ŀ��·��
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
	 * ��ȡ�ϴ��ļ�����·��
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
		//ֻ���������Ա����Ȩ��ɾ���ϴ��ļ�
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
		//ֻ����Ŀ����Ա����Ȩ���ϴ�ģ���ļ�
		if(user != null && isProjectManager(user))
		{			
			//��ȡ�ϴ�Ŀ��·��
			String fileId = UUID.randomUUID().toString();
			String fileName = templateFile.getOriginalFilename();
			File file = getTargetUploadTemplateFile(fileId,fileName);
			
			//���ļ���ŵ�ָ��·��
			templateFile.transferTo(file);
			
			//д�����ݿ�			
			templateDao.insert(newTemplate(fileId,fileName,user));
			return true;
			
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * �½�һ��ģ���ļ�
	 * @param fileId ��fileId��ΪTemplate������id���й���
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
	 * ɾ��ģ���ļ�
	 */
	@Override
	public boolean deleteTemplateFile(String templateId, String userId) 
	{
		User user = userDao.selectByPrimaryKey(userId);
		//ֻ����Ŀ����Ա����Ȩ���ϴ�ģ���ļ�
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
     * ��ȡ�걨���ݵ��ļ�
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
