package com.supinfo.ticketmanager.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
@ManagedBean
@RequestScoped
public class UserController {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    
    public String login() {
    	FacesContext context = FacesContext.getCurrentInstance();
    	HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            req.login(username, password);
            return "home";
        } catch (ServletException e) {
        	ControllerUtils.addErrorMessage("Authentication failed.", "Bad username and/or password.");
            return null;
        }
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
