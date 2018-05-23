package com.wf.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.wf.blog.consts.SessionConstants;
import com.wf.blog.controller.base.BaseController;
import com.wf.blog.entity.Article;
import com.wf.blog.entity.User;
import com.wf.blog.entity.dto.form.AdminUserPwdModifyForm;
import com.wf.blog.entity.dto.form.BlogAddForm;
import com.wf.blog.entity.dto.form.BlogModifyForm;
import com.wf.blog.entity.dto.request.TableKeyModel;
import com.wf.blog.entity.vo.BlogModifyModel;
import com.wf.blog.enums.UserType;
import com.wf.blog.service.api.IAdminBlogService;
import com.wf.blog.service.api.IAdminUserService;
import com.wf.blog.service.api.IResumeService;
import com.wf.blog.service.api.IAdminUserService.ModifyPwdResult;

import static com.wf.blog.consts.ViewConsts.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户管理控制器
 *
 * @author guishenyouhuo
 */
@Controller
@RequestMapping("/user")
public class UserManController extends BaseController {

	@Autowired
	IAdminBlogService mBlogService;
	@Autowired
	IAdminUserService mAdminUserService;
	@Autowired
	IResumeService mResumeService;

	/**
	 * 用户博客管理页面
	 */
	@GetMapping("/blogmanage")
	public String pUserBlogManage(HttpServletRequest request, Model model) {
		return "user/blog_manage";
	}

	/**
	 * 写博客页面
	 */
	@GetMapping("/blogadd")
	public String pUserBlogAdd(HttpServletRequest request, Model model) {
		return "user/blogadd";
	}

	/**
	 * 编辑博客页面
	 */
	@GetMapping("/blogmodify/{id}")
	public String pUserBlogModify(HttpServletRequest request, Model model,
			@PathVariable Integer id) throws Exception {
		Article article = mBlogService.blogSelectByPrimaryKey(id);
		BlogModifyModel modifyModel = new BlogModifyModel(article);
		addModelAtt(model, VIEW_ARTICLE, modifyModel);
		return "user/blog_modify";
	}


	/**
	 * 发布文章
	 */
	@PostMapping("/blogadd.f")
	public String fUserBlogAdd(BlogAddForm blogAddForm, HttpServletRequest request) {
		// 成功则重定向到博客列表
		User user = (User) request.getSession().getAttribute(SessionConstants.SESSION_CURRENT_USER);
		blogAddForm.setUserName(user.getUsername());
		blogAddForm.setUserType(UserType.COMMON_USER.getValue());
		mBlogService.blogAdd(blogAddForm);
		return "redirect:/post";
	}

	/**
	 * 修改文章
	 */
	@PostMapping("blog_modify.f")
	public String fUserBlogModify(BlogModifyForm modifyForm, HttpServletRequest request) {
		// 成功则重定向到博客列表
		User user = (User) request.getSession().getAttribute(SessionConstants.SESSION_CURRENT_USER);
		modifyForm.setUserName(user.getUsername());
		modifyForm.setUserType(UserType.COMMON_USER.getValue());
		mBlogService.blogModify(modifyForm);
		return "redirect:/post";
	}

	/**
	 * 获取博客详情列表
	 */
	@GetMapping("/blog_list.j")
	@ResponseBody
	public Object jUserBlogList(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SessionConstants.SESSION_CURRENT_USER);
		return mBlogService.getArticleListByUser(user.getUsername(), UserType.COMMON_USER.getValue());
	}

	/**
	 * 批量删除博客
	 */
	@DeleteMapping("/blog_delete.j")
	@ResponseBody
	public Object jUserBlogDelete(@RequestBody TableKeyModel model) {
		mBlogService.blogDelete(model);
		return responseSimpleOK();
	}

	/**
	 * 用户个人管理页面
	 */
	@GetMapping("/user_manage")
	public String pUserManage() {
		return "user/user_manage";
	}

	/**
	 * 用户个人管理 json
	 */
	@GetMapping("/user.j")
	@ResponseBody
	public Object jUserList() {
		return mAdminUserService.getAdminUserJson();
	}

	/**
	 * 用户密码修改页面
	 */
	@GetMapping("/user_pwd_modify")
	public String pUserPwdModify() {
		return "user/user_pwd_modify";
	}

	/**
	 * 个人用户密码修改
	 */
	@PostMapping("/user_pwd_modify.f")
	@ResponseBody
	public Object fUserPwdModify(@Valid AdminUserPwdModifyForm form,
			BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			return responseSimpleError();
		}
		ModifyPwdResult result1;
		try {
			result1 = mAdminUserService.modifyUserPwd(form, request);
		} catch (Exception e) {
			e.printStackTrace();
			return responseSimpleError();
		}
		if (result1 == ModifyPwdResult.SUCCESS) {
			return responseSimpleOK();
		} else {
			return responseSimpleError();
		}
	}
}
