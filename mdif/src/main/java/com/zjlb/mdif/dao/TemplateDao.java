package com.zjlb.mdif.dao;

import org.springframework.stereotype.Component;

import com.zjlb.mdif.entity.Template;

@Component
public interface TemplateDao 
{
	int deleteByPrimaryKey(String templateId);

    int insert(Template record);

    int insertSelective(Template record);

    Template selectByPrimaryKey(String templateId);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKey(Template record);
}
