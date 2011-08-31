package com.supinfo.ticketmanager.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketPriority;
import com.supinfo.ticketmanager.entity.TicketStatus;
import com.supinfo.ticketmanager.service.TicketService;
import com.supinfo.ticketmanager.service.UserService;

import fr.bargenson.util.faces.ControllerUtils;

@Named
@SessionScoped
public class TicketController implements Serializable {
	
	@EJB
	private TicketService ticketService;
	
	@EJB
	private UserService userService;
	
	private Ticket ticket;
	private DataModel<Ticket> newTicketsModel;
	private List<SelectItem> priorityItems;
	
	public DataModel<Ticket> getNewTicketsModel() {
		if(newTicketsModel == null) {
			newTicketsModel = new ListDataModel<Ticket>(ticketService.getTicketsByStatus(TicketStatus.NEW));
		}
		return newTicketsModel;
	}
	
	public Ticket getTicket() {
		if(ticket == null) {
			ticket = new Ticket();
		}
		return ticket;
	}
	
	public String addTicket() {
		String username = ControllerUtils.getUserPrincipal().getName();
		ProductOwner reporter = (ProductOwner) userService.findUserByUsername(username);
		ticket.setReporter(reporter);
		ticketService.addTicket(ticket);
		return "newTickets";
	}
	
	public List<SelectItem> getPriorityItems() {
		if(priorityItems == null) {
			ResourceBundle bundle = ControllerUtils.getELValue("msg", ResourceBundle.class);
			priorityItems = new ArrayList<SelectItem>();
			for (TicketPriority priority : TicketPriority.values()) {
				priorityItems.add(new SelectItem(priority, bundle.getString(priority.getBundleKey())));
			}
		}
		return priorityItems;
	}
	
}
