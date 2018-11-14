package com.sos.service;

import com.domain.sos.User;

/**
* @ClassName: LoginService
* @Description: 
* @date 2018年11月14日
*
*/

public interface LoginService {
	
	/**
	 * 登录查询用户
	 */
	User login(String accout, String password);

}
