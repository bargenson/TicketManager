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
	private Ticket ticketToAdd;
	private Ticket addedTicket;
	private ProductOwner reporter;
	private List<Ticket> newTickets;


	public TicketControllerTest() {
		ticketController = new TicketController();

		reporter = new ProductOwner(
				"username", "encryptedPassword", "firstName", 
				"lastName", "email@email.fr", new Date(1234567890)
				);
		reporter.setId(1L);

		ticketToAdd = new Ticket(
				"summary", "description", TicketPriority.MINOR, 
				TicketStatus.NEW, new Date(1234567890), null
				);

		addedTicket = new Ticket(
				"summary", "description", TicketPriority.MINOR, 
				TicketStatus.NEW, new Date(1234567890), reporter
				);
		addedTicket.setId(1L);

		initSimpleNewTickets();
	}

	@Before
	public void setUp() throws Exception {
		TicketService ticketServiceMock = mock(TicketService.class);
		when(ticketServiceMock.getTicketsByStatus(TicketStatus.NEW)).thenReturn(newTickets);
		when(ticketServiceMock.addTicket(ticketToAdd)).thenReturn(addedTicket);

		ReflectionHelper.inject(ticketController, "ticketService", ticketServiceMock);

		UserService userServiceMock = mock(UserService.class);
		when(userServiceMock.findUserByUsername(reporter.getUsername())).thenReturn(reporter);

		ReflectionHelper.inject(ticketController, "userService", userServiceMock);

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
		Principal principalMock = mock(Principal.class);
		when(principalMock.getName()).thenReturn(reporter.getUsername());
		when(controllerHelperMock.getUserPrincipal()).thenReturn(principalMock);

		ReflectionHelper.inject(ticketController, "controllerHelper", controllerHelperMock);
	}

	@Test
	public void testGetNewTicketsModel() {
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
		ReflectionHelper.inject(ticketController, "ticket", ticketToAdd);

		String outcome = ticketController.addTicket();

		assertEquals(TicketController.ADD_TICKET_OUTCOME, outcome);
		assertNotNull(ticketController.getTicket().getReporter());
		assertEquals(reporter, ticketController.getTicket().getReporter());
	}

	@Test
	public void testGetPriorityItems() {
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

}
