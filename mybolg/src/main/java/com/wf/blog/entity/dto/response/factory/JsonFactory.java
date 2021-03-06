package com.wf.blog.entity.dto.response.factory;

import com.wf.blog.entity.dto.response.RedirectResponse;
import com.wf.blog.entity.dto.response.SimpleResponse;



/**
 * Json 实体工厂类接口
 *
 * @author guishenyouhuo
 */
public interface JsonFactory {

  /**
   * 获取简单响应实体
   *
   * @return 响应实体
   */
  SimpleResponse createSimpleResponse();

  /**
   * 获取简单错误实体
   *
   * @return 响应实体
   */
  SimpleResponse createtSimpleErrorResponse();

  /**
   * 获取简单响应实体
   *
   * @param resultCode 结果状态码
   * @param hasError   是否含有错误
   *
   * @return 响应实体
   */
  SimpleResponse createSimpleResponse(int resultCode, boolean hasError);

  /**
   * 获取重定向响应实体
   *
   * @param resultCode  结果状态码
   * @param hasError    是否含有错误
   * @param redirectURL 重定向 url
   *
   * @return
   */
  RedirectResponse createRedirectResponse(int resultCode, boolean hasError, String redirectURL);
}
