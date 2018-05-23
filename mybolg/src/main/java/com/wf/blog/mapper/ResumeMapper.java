package com.wf.blog.mapper;

import com.wf.blog.entity.Resume;
import com.wf.blog.framework.mapper.IMyMapper;

/**
 * 简历
 *
 * @author guishenyouhuo
 */
public interface ResumeMapper extends IMyMapper<Resume> {

  String COLUMN_LIST = "resume.id,title,introduction,resume.gmt_create AS gmtCreate,resume.gmt_modified AS gmtModified";
}