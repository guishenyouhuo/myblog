package com.wf.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import com.wf.blog.entity.Article;

/**
 * 博客批量管理列表视图
 *
 * @author guishenyouhuo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDataGridView {

  private Integer id;

  private String title;

  private Date gmtCreate;

  private Date gmtModified;

  private String introduction;

  public ArticleDataGridView(Article article) {
    this.id = article.getId();
    this.title = article.getTitle();
    this.gmtModified = article.getGmtModified();
    this.gmtCreate = article.getGmtCreate();
    this.introduction = article.getIntroduction();
  }
}