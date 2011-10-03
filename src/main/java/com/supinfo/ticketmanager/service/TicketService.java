package com.supinfo.ticketmanager.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.supinfo.ticketmanager.dao.TicketDao;
import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketStatus;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 23:04
 */
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

}
