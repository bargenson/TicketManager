package com.supinfo.ticketmanager.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import fr.bargenson.util.faces.ControllerHelper;
import fr.bargenson.util.test.ReflectionHelper;

public class UserControllerTest {
	
	private static final String CORRECT_USERNAME = "Developer";
	private static final String CORRECT_PASSWORD = "devdev";
	private static final String BAD_USERNAME = "BadUser";
	private static final String BAD_PASSWORD = "badbad";
	
	
	private UserController userController;
	
	@Before
	public void setUp() throws Exception {
		userController = new UserController();
		
		ControllerHelper controllerHelperMock = mock(ControllerHelper.class);
		doThrow(new ServletException()).when(controllerHelperMock).login(BAD_USERNAME, BAD_PASSWORD);
		doNothing().when(controllerHelperMock).logout();
		
		ReflectionHelper.inject(userController, "controllerHelper", controllerHelperMock);
	}
	
	@Test
	public void testLoginSucceed() {
		userController.setUsername(CORRECT_USERNAME);
		userController.setPassword(CORRECT_PASSWORD);
		
		String outcome = userController.login();
		
		assertEquals(UserController.LOGIN_SUCCEED_OUTCOME, outcome);
	}

	@Test
	public void testLoginFailed() {
		userController.setUsername(BAD_USERNAME);
		userController.setPassword(BAD_PASSWORD);
		
		String outcome = userController.login();
		
		assertEquals(UserController.LOGIN_FAILED_OUTCOME, outcome);
	}
	
	@Test
	public void testLogout() {
		String outcome = userController.logout();
		assertEquals(UserController.LOGOUT_OUTCOME, outcome);
	}
	
}
