package com.wf.blog.mapper;

import com.wf.blog.entity.Article;
import com.wf.blog.entity.dto.form.ArticleSearchForm;
import com.wf.blog.framework.mapper.IMyMapper;
import com.wf.blog.mapper.provider.ArticleSqlProvider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author guishenyouhuo
 */
public interface ArticleMapper extends IMyMapper<Article> {

	String COLUMN_LIST = "article.id,title,introduction,article.gmt_create AS gmtCreate,article.gmt_modified AS gmtModified"
			+ ",article.user_name AS userName,article.user_type AS userType";

	@Select({ "SELECT", COLUMN_LIST, "FROM", "article",
			"ORDER BY article.gmt_create DESC" })
	List<Article> getPostViewAllArticles();

	/**
	 * 通过 tag id 查找文章
	 *
	 * @param id
	 *            tag id
	 *
	 * @return 符合条件的文章
	 */
	@Select({ "SELECT", COLUMN_LIST, "FROM article", "INNER JOIN tag_article",
			"ON tag_article.article_id = article.id",
			"AND tag_article.tag_id=#{id}", "ORDER BY article.gmt_create DESC" })
	List<Article> getArticleListByTagId(Integer id);

	/**
	 * 通过条件查找文章
	 *
	 * @param form
	 *            条件表单
	 *
	 * @return 符合条件的文章
	 */
	@SelectProvider(type = ArticleSqlProvider.class, method = "getArticleByCondition")
	List<Article> getArticleListByCondition(ArticleSearchForm form);
	
	/**
	 * 通过 userName 和userType 查找文章
	 *
	 * @param userName
	 * @param userType
	 *
	 * @return 符合条件的文章
	 */
	@Select({ "SELECT", COLUMN_LIST, "FROM article", "WHERE user_name=#{userName}",
			 "AND user_type=#{userType}", "ORDER BY gmt_create DESC" })
	List<Article> getArticleListByUser(@Param("userName")String userName, @Param("userType")String userType);
}