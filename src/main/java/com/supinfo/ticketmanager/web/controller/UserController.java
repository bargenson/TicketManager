package com.supinfo.ticketmanager.web.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;

import org.hibernate.validator.constraints.NotBlank;

import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.service.UserService;

import fr.bargenson.util.faces.ControllerHelper;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 13:04
 */
@Named
@RequestScoped
public class UserController implements Serializable {
	
	protected static final String LOGIN_SUCCEED_OUTCOME = "newTickets?faces-redirect=true";
	protected static final String LOGIN_FAILED_OUTCOME = null;
	protected static final String LOGOUT_OUTCOME = "login";
	
	
	@Inject
	private ControllerHelper controllerHelper;
	
	@Inject
	private UserService userService;
	
    @NotBlank(message="Username is required")
    private String username;

    @NotBlank(message="Password is required")
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
    
    public boolean isProductOwner() {
    	return isAuthenticated() 
    			&& userService.findUserByUsername(getUsername()) instanceof ProductOwner;
    }

	public String getUsername() {
		if(username == null && isAuthenticated()) {
			username = controllerHelper.getUserPrincipal().getName();
		}
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
