package com.supinfo.ticketmanager.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.supinfo.ticketmanager.dao.TicketDao;
import com.supinfo.ticketmanager.dao.UserDao;
import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketPriority;
import com.supinfo.ticketmanager.entity.TicketStatus;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 23:04
 */
@Stateless
public class TicketService {
	
	@EJB
	private TicketDao ticketDao;
	
	@EJB
	private UserDao userDao;
	
	public void resetTickets() {
		ticketDao.removeAllTickets();
		ProductOwner productOwner = userDao.getAllProductOwners().get(0);
		ticketDao.addTicket(
				new Ticket(
						"AJP13Connection reports \"Bad version\"", 
						"I think I am seeing the same exception with 5.1.12 as was reported here:" +
							"https://sourceforge.net/tracker/?func=detail&atid=107322&aid=1648335&group_id=7322" +
							"This is Jetty 5.1.12, and the bug is really a Blocker bug, as it prevents Jetty from running." +
							"5.1.5 with the same setup works.", 
						TicketPriority.MAJOR, TicketStatus.NEW, 
						new Date(), productOwner
				)
		);
		ticketDao.addTicket(
				new Ticket(
						"Upgrade Maven dependency to Jetty 5.1.12", 
						"Upgrade the Maven Jetty dependency from 5.1.10 to 5.1.12 to include several bug fixes.", 
						TicketPriority.MAJOR, TicketStatus.NEW, 
						new Date(), productOwner
				)
		);
		ticketDao.addTicket(
				new Ticket(
						"Incorrect exception handling when using Hibernate", 
						"ItemProcessor uses Hibernate for persitence. " +
							"During processing of chunk the Database raises an exception " +
							"(i.e. duplicate key - DataIntegrityViolationException).", 
						TicketPriority.CRITICAL, TicketStatus.NEW, 
						new Date(), productOwner
				)
		);
	}

	public List<Ticket> getTicketsByStatus(TicketStatus status) {
		return ticketDao.getAllTickets(status);
	}

}
