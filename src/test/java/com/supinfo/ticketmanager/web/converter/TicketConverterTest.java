package com.supinfo.ticketmanager.web.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;

import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketPriority;
import com.supinfo.ticketmanager.entity.TicketStatus;
import com.supinfo.ticketmanager.service.TicketService;

import fr.bargenson.util.test.ReflectionHelper;

public class TicketConverterTest {

	private TicketConverter ticketConverter;
	private Ticket ticket;

	@Before
	public void setUp() {
		ticketConverter = new TicketConverter();

		ticket = new Ticket(
				"summary", "description", TicketPriority.BLOCKER, 
				TicketStatus.NEW, new Date(), null
				);
		ticket.setId(1L);
	}

	@Test
	public void testGetAsString() {
		FacesContext facesContext = mock(FacesContext.class);
		UIComponent component = mock(UIComponent.class);

		String result = ticketConverter.getAsString(facesContext, component, ticket);
		
		assertEquals(ticket.getId().toString(), result);
	}

	@Test
	public void testGetAsObject() throws Exception {
		FacesContext facesContext = mock(FacesContext.class);
		UIComponent component = mock(UIComponent.class);
		
		TicketService ticketService = mock(TicketService.class);
		when(ticketService.findTicketById(ticket.getId())).thenReturn(ticket);
		injectTicketService(ticketService);
		
		Object result = ticketConverter.getAsObject(facesContext, component, ticket.getId().toString());
		
		assertEquals(ticket, result);
	}

	private void injectTicketService(TicketService ticketService) 
			throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
		
		ReflectionHelper.inject(ticketConverter, "ticketService", ticketService);
	}

}
