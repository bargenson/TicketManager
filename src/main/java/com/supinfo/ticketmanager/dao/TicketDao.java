package com.supinfo.ticketmanager.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketStatus;

@Local
public interface TicketDao extends Serializable {
	
	Ticket addTicket(Ticket ticket);
	List<Ticket> getAllTickets();
	List<Ticket> getAllTickets(TicketStatus status);
	void removeAllTickets();
	Ticket findTicketWithCommentsById(Long l);

}
