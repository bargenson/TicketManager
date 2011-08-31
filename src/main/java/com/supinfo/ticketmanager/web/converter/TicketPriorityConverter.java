package com.supinfo.ticketmanager.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.supinfo.ticketmanager.entity.TicketPriority;

@FacesConverter(forClass=TicketPriority.class)
public class TicketPriorityConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return TicketPriority.valueOf(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		TicketPriority priority = (TicketPriority) value;
		return priority.name();
	}

}
