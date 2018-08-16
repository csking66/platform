package com.es.service;

import com.domain.entity.User;

public interface UserService {

	/**
	 * 根据账号密码登录
	 * @param account
	 * @param password
	 * @param rememberMe
	 * @return
	 */
	public User login(String account, String password);
	
	public void save(User user);
	
	
	
}
