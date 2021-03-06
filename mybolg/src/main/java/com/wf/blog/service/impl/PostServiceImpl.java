package com.wf.blog.service.impl;

import com.wf.blog.entity.Article;
import com.wf.blog.entity.vo.PostView;
import com.wf.blog.entity.dto.form.ArticleSearchForm;
import com.wf.blog.enums.UserType;
import com.wf.blog.mapper.ArticleMapper;
import com.wf.blog.service.api.IPostsService;
import com.wf.blog.service.base.BaseViewTransableService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 博客业务实现类
 *
 * @author guishenyouhuo
 */
@Service
public class PostServiceImpl extends BaseViewTransableService<Article, PostView> implements IPostsService {

  @Autowired
  ArticleMapper mPostMapper;

  @Override
  public List<PostView> getPostList(String userName) {
    List<Article> articles = null;
    if(StringUtils.isEmpty(userName)){
    	articles = mPostMapper.getPostViewAllArticles();
    } else {
    	articles = mPostMapper.getArticleListByUser(userName, UserType.COMMON_USER.getValue());
    }
    List<PostView> postViewList = transEntityToView(articles);
    return postViewList;
  }

  @Override
  @Deprecated
  public List<PostView> getPostListByDate(Date start, Date end) {
    return null;
  }

  @Override
  public List<PostView> getPostListByTagId(Integer tagId) {
    List<Article> articlelist = mPostMapper.getArticleListByTagId(tagId);
    List<PostView> postViewList = transEntityToView(articlelist);
    return postViewList;
  }

  @Override
  public List<PostView> getPostListByArticleCondition(ArticleSearchForm form) {
    Article article = new Article();
    article.setTitle(form.getName());
    List<Article> articleList = mPostMapper.getArticleListByCondition(form);
    return transEntityToView(articleList);
  }

  @Override
  protected List<PostView> transEntityToView(List<Article> entityList) {
    List<PostView> postViewsList = new ArrayList<>();
    Iterator it = entityList.iterator();
    while (it.hasNext()) {
      Article article = (Article) it.next();
      PostView postView = new PostView(article);
      postViewsList.add(postView);
    }
    return postViewsList;
  }
}
