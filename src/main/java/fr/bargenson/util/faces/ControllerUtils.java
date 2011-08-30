package fr.bargenson.util.faces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 23:00
 */
public class ControllerUtils {
	
	public static void addErrorMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    	FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
