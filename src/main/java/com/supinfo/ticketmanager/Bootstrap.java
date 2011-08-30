package com.supinfo.ticketmanager;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.supinfo.ticketmanager.service.TicketService;
import com.supinfo.ticketmanager.service.UserService;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 11:46
 */
@WebListener
public class Bootstrap implements ServletContextListener {

	@EJB
	private UserService userService;
	
	@EJB
	private TicketService ticketService;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		userService.resetUsers();
		ticketService.resetTickets();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Do nothing
	}

}
