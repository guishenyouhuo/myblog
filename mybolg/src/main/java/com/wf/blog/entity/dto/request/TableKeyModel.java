package com.wf.blog.entity.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 批量操作对主键的封装
 *
 * @author guishenyouhuo
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TableKeyModel {

  private List<Integer> ids;
}
