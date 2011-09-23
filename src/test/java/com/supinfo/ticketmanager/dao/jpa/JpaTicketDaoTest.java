package com.supinfo.ticketmanager.dao.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dbunit.DatabaseUnitException;
import org.hibernate.Session;
import org.hibernate.ejb.EntityManagerImpl;
import org.hibernate.jdbc.Work;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.supinfo.ticketmanager.dao.TicketDao;
import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketPriority;
import com.supinfo.ticketmanager.entity.TicketStatus;

import fr.bargenson.util.test.dbunit.DbUnitUtils;

@RunWith(Arquillian.class)
public class JpaTicketDaoTest {

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(TicketDao.class, JpaTicketDao.class)
				.addPackage(Ticket.class.getPackage())
				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
				.addAsResource(new File("src/test/resources/META-INF/persistence.xml"), "META-INF/persistence.xml");
	}

	
	@EJB TicketDao ticketDao;

	@PersistenceContext EntityManager em;

	
	@Before
	public void setUp() throws DatabaseUnitException, SQLException {
		final Session session = ((EntityManagerImpl)em.getDelegate()).getSession();
		session.doWork(new Work() {	
			@Override
			public void execute(Connection connection) throws SQLException {
				DbUnitUtils.loadDataset(connection, "src/test/resources/db/insert-data.xml");
			}
		});
	}

	@Test
	public void testAddTicket() {
		ProductOwner po = new ProductOwner();
		po.setId(1L);
		
		Ticket ticket = new Ticket(
				"summary", "description", TicketPriority.CRITICAL, 
				TicketStatus.NEW, new Date(), po
		);
		
		Ticket persistedTicket = ticketDao.addTicket(ticket);
		assertNotNull(persistedTicket);
		assertNotNull(persistedTicket.getId());
		
		Ticket retrievedTicket = em.find(Ticket.class, persistedTicket.getId());
		assertEquals(persistedTicket, retrievedTicket);
	}

	@Test
	public void testGetAllTickets() {
		List<Ticket> tickets = ticketDao.getAllTickets();
		assertNotNull(tickets);
		assertEquals(1, tickets.size());
	}

	@Test
	public void testGetAllTicketsByStatus() {
		List<Ticket> newTickets = ticketDao.getAllTickets(TicketStatus.NEW);
		assertNotNull(newTickets);
		assertEquals(1, newTickets.size());

		List<Ticket> inProgressTickets = ticketDao.getAllTickets(TicketStatus.IN_PROGRESS);
		assertNotNull(inProgressTickets);
		assertEquals(0, inProgressTickets.size());
	}

}
