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
 * 项目管理员对应的业务逻辑
 * @author Administrator
 *
 */
public interface ProjectManagerService 
{

    /**
     * 项目管理员获取当前项目的上报信息列表
     * @param userId
     * @return
     */
    List<ProjectListDto> selectProject(String userId);
    
    /**
     * 获取UserId对应的项目管理员的所有操作员信息
     * @param userId
     * @return
     */
    List<User> selectProjectUsers(String userId);
    
    /**
     *  删除操作员
     * @param userId
     * @return
     */
    boolean deleteUser(String userId,String sessionUserId);
    
    /**
     * 添加操作员
     * @param user
     * @param sessionUserId
     * @return
     */
    boolean addUser(User user,String sessionUserId) throws Exception;
    
    /**
     * 上传申报数据
     * @param sourceFile 区域操作员上传的申报数据
     * @param userId 当前用户
     * @return 成功或失败
     */
    boolean uploadFiles(MultipartFile sourceFile, String userId,String month) throws IllegalStateException, IOException;
    
    /**
     * 删除文件
     * @param uploadId
     * @return
     */
    boolean deleteUploadFile(String uploadId, String userId);
    
    /**
     * 上传模板文件
     * @param templateFile 模板文件
     * @param userId 当前用户
     * @return 成功或失败
     */
    boolean uploadTemplate(MultipartFile templateFile, String userId) throws IllegalStateException, IOException;
    
    /**
     * 删除文件
     * @param templateId
     * @return
     */
    boolean deleteTemplateFile(String templateId, String userId);
    
    /**
     * 获取下载文件的文件名
     * @param uploadId
     * @return
     */
    String GetFileName(String uploadId);
    
    /**
     * 获取要下载的文件
     * @param uploadId
     * @return
     */
    File GetFile(String uploadId);
    
    /**
     * 获取下载模板文件的文件名
     * @param templateId
     * @return
     */
    String GetTempateFileName(String templateId);
    
    /**
     * 获取要下载模板的文件
     * @param templateId
     * @return
     */
    File GetTemplateFile(String templateId);
}
