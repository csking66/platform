package com.sos.service;

import com.domain.sos.User;

/**
* @ClassName: LoginService
* @Description: 
* @date 2018年11月15日
*
*/

public interface LoginService {

	User login(String accout, String password);
}
