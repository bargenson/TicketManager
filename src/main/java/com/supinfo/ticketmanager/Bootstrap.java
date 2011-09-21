package com.supinfo.ticketmanager;

import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.supinfo.ticketmanager.entity.Developer;
import com.supinfo.ticketmanager.entity.ProductOwner;
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
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		userService.register(
				new ProductOwner(
						"ProductOwner", "plop", "plop", "Bob", 
						"Master", "bob@ticketmanager.com", new Date()
				)
		);
		userService.register(
				new Developer(
						"Developer", "plop", "plop", "Linus", 
						"Torvald", "linus@ticketmanager.com", new Date()
				)
		);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Do nothing
	}

}
