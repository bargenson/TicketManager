//package com.supinfo.ticketmanager.dao.jpa;
//
//import static org.junit.Assert.fail;
//
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import javax.ejb.embeddable.EJBContainer;
//import javax.naming.Context;
//import javax.naming.NamingException;
//import javax.persistence.EntityManager;
//
//import org.dbunit.DatabaseUnitException;
//import org.dbunit.database.DatabaseConnection;
//import org.dbunit.database.IDatabaseConnection;
//import org.dbunit.dataset.xml.FlatXmlDataSet;
//import org.dbunit.dataset.xml.FlatXmlProducer;
//import org.dbunit.operation.DatabaseOperation;
//import org.hibernate.Session;
//import org.hibernate.ejb.EntityManagerImpl;
//import org.hibernate.jdbc.Work;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.xml.sax.InputSource;
//
//public class AbstractDaoTest {
//	
//	private static EJBContainer ejbContainer;
//	private static Context ejbContext;
//	
//	protected static EntityManager em;
//	
//	@BeforeClass
//	public static void setUp() throws Exception {
//		ejbContainer = EJBContainer.createEJBContainer();
//		ejbContext = ejbContainer.getContext();
//		
//		em = (EntityManager) ejbContext.lookup("java:comp/env/persistence/em");
//
//		Session session = ((EntityManagerImpl)em.getDelegate()).getSession();
//		session.doWork(new Work() {	
//			@Override
//			public void execute(Connection connection) throws SQLException {
//				try {
//					IDatabaseConnection dbUnitconnection = new DatabaseConnection(connection);
//					InputStream datasetStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db/insert-data.xml");
//					FlatXmlProducer producer = new FlatXmlProducer(new InputSource(datasetStream));
//					FlatXmlDataSet dataset = new FlatXmlDataSet(producer);
//					DatabaseOperation.CLEAN_INSERT.execute(dbUnitconnection, dataset);
//					dbUnitconnection.close();
//				} catch (DatabaseUnitException e) {
//					e.printStackTrace();
//					fail("Impossible to init DB Unit.");
//				}
//			}
//		});
//	}
//
//	@AfterClass
//	public static void tearDown() throws Exception {
//		em.close();
//		ejbContext.close();
//		ejbContainer.close();
//	}
//	
//	@SuppressWarnings("unchecked")
//	public static <T> T getEJB(Class<T> clazz) throws NamingException {
//		return (T) ejbContext.lookup("java:global/classes/" + clazz.getSimpleName());
//	}
//
//}
