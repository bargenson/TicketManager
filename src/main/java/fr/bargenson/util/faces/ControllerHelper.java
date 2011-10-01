package fr.bargenson.util.faces;

import java.io.Serializable;
import java.security.Principal;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 23:00
 */
@Named
public class ControllerHelper implements Serializable {
	
	public ControllerHelper() {
		
	}
	
	public ResourceBundle getResourceBundle(String resourceBundleId) {
		return getELValue(resourceBundleId, ResourceBundle.class);
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getELValue(String name, Class<T> cls) {
		return (T)  
				FacesContext.getCurrentInstance()
							.getApplication()
							.getExpressionFactory()
							.createValueExpression(
								   FacesContext.getCurrentInstance().getELContext(), 
								   "#{" + name + "}",
								   cls)
							.getValue(FacesContext.getCurrentInstance().getELContext());
	}
	
	public HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public Principal getUserPrincipal() {
		return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
	}
	
	public void addErrorMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void login(String username, String password) throws ServletException {
		getHttpServletRequest().login(username, password);
	}
	
	public void logout() throws ServletException {
		getHttpServletRequest().logout();
	}
	
	public String getRequestParam(String paramName) {
		return getHttpServletRequest().getParameter(paramName);
	}

}
