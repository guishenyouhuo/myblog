package com.wf.blog.controller;

import com.wf.blog.controller.base.BaseController;
import com.wf.blog.entity.AdminUser;
import com.wf.blog.entity.dto.form.UserLoginForm;
import com.wf.blog.service.api.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 后台登录控制器
 *
 * @author guishenyouhuo
 */
@Controller
public class AdminLoginController extends BaseController {

  @Autowired
  private IAdminUserService mAdminService;

  /**
   * 后台用户登录页面
   */
  @GetMapping("/adminlogin")
  public String pAdminLogin() {
    return "admin/userlogin";
  }

  /**
   * 后台用户登录页面
   */
  @GetMapping("/adminlogin/index")
  public String pAdminLoginIndex() {
    return "admin/userlogin";
  }

  /**
   * 后台用户登录验证
   */
  @PostMapping("/adminlogin/login.f")
  public String fAdminLogin(@Valid UserLoginForm userLoginForm, BindingResult bindingResult, HttpServletRequest request) {
    if (bindingResult.hasErrors()) {
      List<ObjectError> errors = bindingResult.getAllErrors();
      return "redirect:/adminlogin?msg=" + errors.get(0).getDefaultMessage();
    }
    AdminUser user = mAdminService.checkAdminUserExist(userLoginForm);
    if (null != user) {
      mAdminService.joinSession(request, user);
      return "redirect:/admin/index";
    }
    return "redirect:/adminlogin?msg=登录失败";
  }
}
