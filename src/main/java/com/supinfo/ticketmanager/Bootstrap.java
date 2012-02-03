package com.supinfo.ticketmanager;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.supinfo.ticketmanager.dao.UserDao;
import com.supinfo.ticketmanager.entity.Developer;
import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.entity.User;

import fr.bargenson.util.crypto.MD5Digester;

@WebListener
public class Bootstrap implements ServletContextListener {

	@Inject
	private UserDao userDao;
	
	private MD5Digester digester = new MD5Digester();
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		Developer developer = new Developer(
				"Developer", digester.digest("devdev"), "Linus", 
				"Torvald", "linus@linux.org", new Date()
				);
		
		ProductOwner productOwner = new ProductOwner(
				"ProductOwner", digester.digest("popo"), "Steve", 
				"Jobs", "steve@apple.com", new Date()
				);
		
		addUserIfDoesntExist(developer);
		addUserIfDoesntExist(productOwner);
	}

	private void addUserIfDoesntExist(User user) {
		try {
			userDao.findUserByUsername(user.getUsername());
		} catch (Exception e) {
			userDao.addUser(user);
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Do nothing
	}

}
