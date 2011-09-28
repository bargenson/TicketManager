package com.supinfo.ticketmanager.web.faces.mock;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.supinfo.ticketmanager.web.controller.UserController;

@Named("userController")
@ApplicationScoped
public class UserControllerMock extends UserController {
	
	@Override
	public String login() {
		return null;
	}

}
