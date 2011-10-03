package com.supinfo.ticketmanager.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketPriority;
import com.supinfo.ticketmanager.entity.TicketStatus;
import com.supinfo.ticketmanager.service.TicketService;
import com.supinfo.ticketmanager.service.UserService;

import fr.bargenson.util.faces.ControllerHelper;

@Named
@RequestScoped
public class TicketController implements Serializable {
	
	private static final long serialVersionUID = 354054054054L;
	
	
	protected static final String ADD_TICKET_OUTCOME = "newTickets?faces-redirect=true";

	@Inject
	private TicketService ticketService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private ControllerHelper controllerHelper;
	
	private Ticket ticket;
	private transient DataModel<Ticket> newTicketsModel;
	private transient List<SelectItem> priorityItems;
	
	public DataModel<Ticket> getNewTicketsModel() {
		if(newTicketsModel == null) {
			newTicketsModel = new ListDataModel<Ticket>(ticketService.getTicketsByStatus(TicketStatus.NEW));
		}
		return newTicketsModel;
	}
	
	public String addTicket() {
		String username = controllerHelper.getUserPrincipal().getName();
		ProductOwner reporter = (ProductOwner) userService.findUserByUsername(username);
		ticket.setReporter(reporter);
		ticketService.addTicket(ticket);
		return ADD_TICKET_OUTCOME;
	}
	
	public List<SelectItem> getPriorityItems() {
		if(priorityItems == null) {
			ResourceBundle bundle = controllerHelper.getResourceBundle("msg");
			priorityItems = new ArrayList<SelectItem>();
			for (TicketPriority priority : TicketPriority.values()) {
				priorityItems.add(new SelectItem(priority, bundle.getString(priority.getBundleKey())));
			}
		}
		return priorityItems;
	}

	public Ticket getTicket() {
		if(ticket == null) {
			ticket = new Ticket();
		}
		return ticket;
	}
	
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
}
