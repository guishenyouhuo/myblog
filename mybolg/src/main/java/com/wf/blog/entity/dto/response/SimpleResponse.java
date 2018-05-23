package com.wf.blog.entity.dto.response;

/**
 * 简单 Json 响应类
 *
 * @author guishenyouhuo
 */
public class SimpleResponse extends GlobalResponse {

  public SimpleResponse(int resultCode, boolean hasError) {
    super(resultCode, hasError);
  }
}
