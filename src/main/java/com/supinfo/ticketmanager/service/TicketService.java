package com.supinfo.ticketmanager.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.supinfo.ticketmanager.dao.TicketDao;
import com.supinfo.ticketmanager.entity.Developer;
import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketStatus;

@Stateless
public class TicketService implements Serializable {
	
	@Inject
	private TicketDao ticketDao;
	

	public List<Ticket> getTicketsByStatus(TicketStatus status) {
		return ticketDao.getAllTickets(status);
	}

	public Ticket addTicket(Ticket ticket) {
		ticket.setStatus(TicketStatus.NEW);
		ticket.setCreatedAt(new Date());
		return ticketDao.addTicket(ticket);
	}
	
	public Ticket findTicketById(Long ticketId) {
		return ticketDao.findTicketWithCommentsById(ticketId);
	}

    public void assignTicket(Ticket ticket, Developer developer) {
        ticket.setDeveloper(developer);
        ticket.setStatus(TicketStatus.IN_PROGRESS);
        ticketDao.updateTicket(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    public List<Ticket> getTicketsByDeveloper(Developer developer) {
        return ticketDao.getTicketsByDeveloper(developer);
    }
}
