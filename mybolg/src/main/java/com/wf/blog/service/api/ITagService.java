package com.wf.blog.service.api;

import com.wf.blog.entity.vo.TagView;

import java.util.List;

/**
 * 文章标签相关业务接口
 *
 * @author guishenyouhuo
 */
public interface ITagService {

  /**
   * 获取所有 Tag 视图
   *
   * @return Tag 视图
   */
  List<TagView> getAllTagView();
}
