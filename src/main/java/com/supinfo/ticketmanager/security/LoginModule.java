package com.supinfo.ticketmanager.security;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.supinfo.ticketmanager.service.UserService;

import fr.bargenson.util.security.AbstractLoginModule;
import fr.bargenson.util.security.UserInfo;

public class LoginModule extends AbstractLoginModule {
	
	private UserService userService;
	
	public LoginModule() throws NamingException {
		Context context = new InitialContext();
		userService = (UserService) context.lookup("java:module/UserService");
	}

	@Override
	protected UserInfo getUserInfo(String username) throws Exception {
		return userService.getUserInfo(username);
	}

}
