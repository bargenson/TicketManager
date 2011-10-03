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
	public void setUp() {
		userController = new UserController();
	}

	@Test
	public void testLoginSucceed() throws Exception {
		ControllerHelper controllerHelperMock = mock(ControllerHelper.class);
		doNothing().when(controllerHelperMock).login(CORRECT_USERNAME, CORRECT_PASSWORD);
		injectControllerHelper(controllerHelperMock);
		
		userController.setUsername(CORRECT_USERNAME);
		userController.setPassword(CORRECT_PASSWORD);

		String outcome = userController.login();

		assertEquals(UserController.LOGIN_SUCCEED_OUTCOME, outcome);
	}

	@Test
	public void testLoginFailed() throws Exception {
		ControllerHelper controllerHelperMock = mock(ControllerHelper.class);
		doThrow(new ServletException()).when(controllerHelperMock).login(BAD_USERNAME, BAD_PASSWORD);
		injectControllerHelper(controllerHelperMock);

		userController.setUsername(BAD_USERNAME);
		userController.setPassword(BAD_PASSWORD);

		String outcome = userController.login();

		assertEquals(UserController.LOGIN_FAILED_OUTCOME, outcome);
	}

	@Test
	public void testLogout() throws Exception {
		ControllerHelper controllerHelperMock = mock(ControllerHelper.class);
		doNothing().when(controllerHelperMock).logout();
		injectControllerHelper(controllerHelperMock);
		
		String outcome = userController.logout();
		assertEquals(UserController.LOGOUT_OUTCOME, outcome);
	}

	private void injectControllerHelper(ControllerHelper controllerHelperMock) 
			throws IllegalAccessException, NoSuchFieldException {
		
		ReflectionHelper.inject(userController, "controllerHelper", controllerHelperMock);
	}

}
