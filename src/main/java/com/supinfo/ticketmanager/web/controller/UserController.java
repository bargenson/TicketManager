package com.supinfo.ticketmanager.web.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.constraints.NotBlank;

import fr.bargenson.util.faces.ControllerUtils;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 13:04
 */
@Named
@SessionScoped
public class UserController implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    
    public String login() {
    	HttpServletRequest req = ControllerUtils.getHttpServletRequest();
        try {
            req.login(username, password);
            return "newTickets";
        } catch (ServletException e) {
        	ControllerUtils.addErrorMessage("Authentication failed.", "Bad username and/or password.");
            return null;
        }
    }
    
    public String logout() {
    	try {
			ControllerUtils.getHttpServletRequest().logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
    	return "login";
    }
    
    public boolean isAuthenticated() {
    	return ControllerUtils.getHttpServletRequest().getUserPrincipal() != null;
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
