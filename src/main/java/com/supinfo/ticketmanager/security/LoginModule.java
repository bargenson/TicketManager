package com.supinfo.ticketmanager.security;

import javax.ejb.EJB;

import com.supinfo.ticketmanager.service.UserService;

import fr.bargenson.util.security.AbstractLoginModule;
import fr.bargenson.util.security.UserInfo;

public class LoginModule extends AbstractLoginModule {
	
	@EJB
	private UserService userService;

	@Override
	protected UserInfo getUserInfo(String username) throws Exception {
		return userService.getUserInfo(username);
	}

}
