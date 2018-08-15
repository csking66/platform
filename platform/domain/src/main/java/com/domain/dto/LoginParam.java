package com.domain.dto;

import io.swagger.annotations.ApiModelProperty;

public class LoginParam {

	@ApiModelProperty(value = "用户名", required = true)
	private String account;

	@ApiModelProperty(value = "密码", required = true)
	private String password;
	
	@ApiModelProperty(value = "是否记住我,默认否")
	private boolean rememberMe;
	
	@ApiModelProperty(value = "登录语种，默认中文简体")
	private String language = "zh_CN";

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
