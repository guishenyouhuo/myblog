package com.wf.blog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wf.blog.consts.SessionConstants;
import com.wf.blog.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限验证的拦截器
 *
 * @author guishenyouhuo
 */
@Component
public class UserAuthenticationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object obj = request.getSession().getAttribute(
				SessionConstants.SESSION_CURRENT_USER);
		if (null == obj || !(obj instanceof User)) {
			response.sendRedirect(request.getContextPath() + "/userlogin");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
