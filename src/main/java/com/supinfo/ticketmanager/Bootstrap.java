package com.supinfo.ticketmanager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 11:46
 */
@WebListener
public class Bootstrap implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// Do Nothing
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Do nothing
	}

}
