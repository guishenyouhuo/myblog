package com.wf.blog.service.impl;

import com.wf.blog.entity.Tag;
import com.wf.blog.entity.vo.TagView;
import com.wf.blog.mapper.TagMapper;
import com.wf.blog.service.api.ITagService;
import com.wf.blog.service.base.BaseViewTransableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章标签相关业务实现类
 *
 * @author James
 */
@Service
public class TagServiceImpl extends BaseViewTransableService<Tag, TagView> implements ITagService {

  @Autowired
  TagMapper mTagMapper;

  @Override
  protected List<TagView> transEntityToView(List<Tag> entityList) {
    List<TagView> tagViews = new ArrayList<>();
    for (Tag tag : entityList) {
      TagView tagView = new TagView(tag);
      tagViews.add(tagView);
    }
    return tagViews;
  }

  @Override
  public List<TagView> getAllTagView() {
    return mTagMapper.selectAllTagView();
  }
}
