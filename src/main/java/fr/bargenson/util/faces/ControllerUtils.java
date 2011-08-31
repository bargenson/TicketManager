package fr.bargenson.util.faces;

import java.security.Principal;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 23:00
 */
public final class ControllerUtils {
	
	private ControllerUtils() { }
	
	@SuppressWarnings("unchecked")
	public final static <T> T getELValue(String name, Class<T> cls) {
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
	
	public final static HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public final static Principal getUserPrincipal() {
		return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
	}
	
	public final static void addErrorMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    	FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
