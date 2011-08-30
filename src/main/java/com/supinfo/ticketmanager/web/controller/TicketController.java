package com.supinfo.ticketmanager.web.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketStatus;
import com.supinfo.ticketmanager.service.TicketService;

@ManagedBean
@RequestScoped
public class TicketController {
	
	@EJB
	private TicketService ticketService;
	
	public DataModel<Ticket> getNewTickets() {
		return new ListDataModel<Ticket>(ticketService.getTicketsByStatus(TicketStatus.NEW));
	}

}
