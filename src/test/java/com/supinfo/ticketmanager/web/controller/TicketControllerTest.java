package com.supinfo.ticketmanager.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;

import org.junit.Before;
import org.junit.Test;

import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketPriority;
import com.supinfo.ticketmanager.entity.TicketStatus;
import com.supinfo.ticketmanager.service.TicketService;
import com.supinfo.ticketmanager.service.UserService;

import fr.bargenson.util.faces.ControllerHelper;
import fr.bargenson.util.test.ReflectionHelper;

public class TicketControllerTest {

	private TicketController ticketController;
	
	private Ticket initialTicket;
	private Ticket ticketToAdd;
	private Ticket addedTicket;
	private Ticket ticketToFind;
	private ProductOwner reporter;
	private List<Ticket> newTickets;

	@Before
	public void setUp() {
		ticketController = new TicketController();
		
		reporter = new ProductOwner(
				"username", "encryptedPassword", "firstName", 
				"lastName", "email@email.fr", new Date(1234567890)
				);
		reporter.setId(1L);

		initialTicket = new Ticket("summary", "description", TicketPriority.MINOR, null, null, null);
		
		ticketToAdd = new Ticket(
				initialTicket.getSummary(), initialTicket.getDescription(), 
				initialTicket.getPriority(), null, null, reporter
				);

		addedTicket = new Ticket(
				ticketToAdd.getSummary(), ticketToAdd.getDescription(), 
				ticketToAdd.getPriority(), TicketStatus.NEW, new Date(), reporter
				);
		addedTicket.setId(1L);

		ticketToFind = new Ticket(
				"summary", "description", TicketPriority.MINOR, 
				TicketStatus.NEW, new Date(1234567890), null
				);
		ticketToFind.setId(1L);

		initSimpleNewTickets();
	}	

	@Test
	public void testGetNewTicketsModel() throws Exception {
		TicketService ticketServiceMock = mock(TicketService.class);
		when(ticketServiceMock.getTicketsByStatus(TicketStatus.NEW)).thenReturn(newTickets);
		injectTicketService(ticketServiceMock);
		
		DataModel<Ticket> model = ticketController.getNewTicketsModel();

		assertNotNull(model);

		assertEquals(newTickets.size(), model.getRowCount());
		assertEquals(newTickets, model.getWrappedData());
	}

	@Test
	public void testGetTicket() {
		Ticket ticket = ticketController.getTicket();

		assertNotNull(ticket);

		final String simpleSummary = "Summary";
		ticketController.getTicket().setSummary(simpleSummary);

		assertEquals(simpleSummary, ticketController.getTicket().getSummary());
	}

	@Test
	public void testAddTicket() throws Exception {
		ControllerHelper controllerHelperMock = mock(ControllerHelper.class);
		Principal principalMock = mock(Principal.class);
		when(principalMock.getName()).thenReturn(reporter.getUsername());
		when(controllerHelperMock.getUserPrincipal()).thenReturn(principalMock);
		injectControllerHelper(controllerHelperMock);

		TicketService ticketServiceMock = mock(TicketService.class);
		when(ticketServiceMock.addTicket(ticketToAdd)).thenReturn(addedTicket);
		injectTicketService(ticketServiceMock);
		
		UserService userServiceMock = mock(UserService.class);
		when(userServiceMock.findUserByUsername(reporter.getUsername())).thenReturn(reporter);
		injectUserService(userServiceMock);
		
		ticketController.setTicket(initialTicket);

		String outcome = ticketController.addTicket();

		assertEquals(TicketController.ADD_TICKET_OUTCOME, outcome);
	}

	@Test
	public void testGetPriorityItems() throws Exception {
		ControllerHelper controllerHelperMock = mock(ControllerHelper.class);
		when(controllerHelperMock.getResourceBundle("msg")).thenReturn(new ResourceBundle() {
			
			@Override
			protected Object handleGetObject(String key) {
				return "";
			}
			
			@Override
			public Enumeration<String> getKeys() {
				return null;
			}
		});
		injectControllerHelper(controllerHelperMock);
		
		List<SelectItem> items = ticketController.getPriorityItems();

		assertNotNull(items);
		assertEquals(TicketPriority.values().length, items.size());
		for (SelectItem selectItem : items) {
			assertNotNull((TicketPriority) selectItem.getValue());
		}
	}

	private void initSimpleNewTickets() {
		newTickets = new ArrayList<Ticket>();

		newTickets.add(
				new Ticket(
						"summary1", "description1", TicketPriority.MAJOR, 
						TicketStatus.NEW, new Date(1234567890), null
						)
				);

		newTickets.add(
				new Ticket(
						"summary2", "description2", TicketPriority.MAJOR, 
						TicketStatus.NEW, new Date(1234567890), null
						)
				);
	}
	
	private void injectTicketService(TicketService ticketService)
			throws IllegalAccessException, NoSuchFieldException {

		ReflectionHelper.inject(ticketController, "ticketService", ticketService);
	}
	
	private void injectUserService(UserService userService)
			throws IllegalAccessException, NoSuchFieldException {
		
		ReflectionHelper.inject(ticketController, "userService", userService);
	}
	
	private void injectControllerHelper(ControllerHelper controllerHelperMock)
			throws IllegalAccessException, NoSuchFieldException {
		
		ReflectionHelper.inject(ticketController, "controllerHelper", controllerHelperMock);
	}

}
