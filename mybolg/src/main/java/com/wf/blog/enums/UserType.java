package com.wf.blog.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserType {

	ADMIN_USER("admin_user", "管理员用户"),
	COMMON_USER("user", "普通用户");
	
	private String value;
	
	private String desc;
	
	UserType(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
	
	public static UserType parse(String userType){
		UserType[] values = UserType.values();
		for (UserType ut : values) {
			if(StringUtils.equals(ut.getValue(), userType)){
				return ut;
			}
		}
		return null;
	}
}
