package com.supinfo.ticketmanager.web.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;

import org.hibernate.validator.constraints.NotBlank;

import fr.bargenson.util.faces.ControllerHelper;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 13:04
 */
@Named
@SessionScoped
public class UserController {
	
	protected static final String LOGIN_SUCCEED_OUTCOME = "newTickets";
	protected static final String LOGIN_FAILED_OUTCOME = null;
	protected static final String LOGOUT_OUTCOME = "login";
	
	
	@Inject
	private ControllerHelper controllerHelper;
	
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    
    public String login() {
        try {
        	controllerHelper.login(username, password);
            return LOGIN_SUCCEED_OUTCOME;
        } catch (ServletException e) {
        	controllerHelper.addErrorMessage("Authentication failed.", "Bad username and/or password.");
            return LOGIN_FAILED_OUTCOME;
        }
    }
    
    public String logout() {
    	try {
    		controllerHelper.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
    	return LOGOUT_OUTCOME;
    }
    
    public boolean isAuthenticated() {
    	return controllerHelper.getUserPrincipal() != null;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
