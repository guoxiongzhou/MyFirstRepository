package com.zjlb.mdif.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zjlb.mdif.entity.ProjectListDto;
import com.zjlb.mdif.entity.ResultDto;
import com.zjlb.mdif.entity.User;

/**
 * ��Ŀ����Ա��Ӧ��ҵ���߼�
 * @author Administrator
 *
 */
public interface ProjectManagerService 
{

    /**
     * ��Ŀ����Ա��ȡ��ǰ��Ŀ���ϱ���Ϣ�б�
     * @param userId
     * @return
     */
    List<ProjectListDto> selectProject(String userId);
    
    /**
     * ��ȡUserId��Ӧ����Ŀ����Ա�����в���Ա��Ϣ
     * @param userId
     * @return
     */
    List<User> selectProjectUsers(String userId);
    
    /**
     *  ɾ������Ա
     * @param userId
     * @return
     */
    boolean deleteUser(String userId,String sessionUserId);
    
    /**
     * ��Ӳ���Ա
     * @param user
     * @param sessionUserId
     * @return
     */
    boolean addUser(User user,String sessionUserId) throws Exception;
    
    /**
     * �ϴ��걨����
     * @param sourceFile �������Ա�ϴ����걨����
     * @param userId ��ǰ�û�
     * @return �ɹ���ʧ��
     */
    boolean uploadFiles(MultipartFile sourceFile, String userId,String month) throws IllegalStateException, IOException;
    
    /**
     * ɾ���ļ�
     * @param uploadId
     * @return
     */
    boolean deleteUploadFile(String uploadId, String userId);
    
    /**
     * �ϴ�ģ���ļ�
     * @param templateFile ģ���ļ�
     * @param userId ��ǰ�û�
     * @return �ɹ���ʧ��
     */
    boolean uploadTemplate(MultipartFile templateFile, String userId) throws IllegalStateException, IOException;
    
    /**
     * ɾ���ļ�
     * @param templateId
     * @return
     */
    boolean deleteTemplateFile(String templateId, String userId);
    
    /**
     * ��ȡ�����ļ����ļ���
     * @param uploadId
     * @return
     */
    String GetFileName(String uploadId);
    
    /**
     * ��ȡҪ���ص��ļ�
     * @param uploadId
     * @return
     */
    File GetFile(String uploadId);
    
    /**
     * ��ȡ����ģ���ļ����ļ���
     * @param templateId
     * @return
     */
    String GetTempateFileName(String templateId);
    
    /**
     * ��ȡҪ����ģ����ļ�
     * @param templateId
     * @return
     */
    File GetTemplateFile(String templateId);
}
