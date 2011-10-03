package com.supinfo.ticketmanager.web.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;

import com.supinfo.ticketmanager.entity.TicketPriority;


public class TicketPriorityConverterTest {
	
	private TicketPriorityConverter ticketPriorityConverter;
	
	@Before
	public void setUp() {
		ticketPriorityConverter = new TicketPriorityConverter();
	}
	
	@Test
	public void testGetAsString() {
		FacesContext facesContext = mock(FacesContext.class);
		UIComponent component = mock(UIComponent.class);

		for (TicketPriority priority : TicketPriority.values()) {
			String result = ticketPriorityConverter.getAsString(facesContext, component, priority);
			assertEquals(priority.toString(), result);
		}
	}

	@Test
	public void testGetAsObject() throws Exception {
		FacesContext facesContext = mock(FacesContext.class);
		UIComponent component = mock(UIComponent.class);
		
		for (TicketPriority priority : TicketPriority.values()) {
			Object result = ticketPriorityConverter.getAsObject(facesContext, component, priority.toString());
			assertEquals(priority, result);
		}
	}

}
