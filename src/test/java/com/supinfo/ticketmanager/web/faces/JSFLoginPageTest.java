//package com.supinfo.ticketmanager.web.faces;
//
//import static org.junit.Assert.assertEquals;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.jsfunit.api.InitialPage;
//import org.jboss.jsfunit.jsfsession.JSFClientSession;
//import org.jboss.jsfunit.jsfsession.JSFServerSession;
//import org.jboss.shrinkwrap.api.ArchivePaths;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import com.supinfo.ticketmanager.web.faces.mock.UserControllerMock;
//
//import fr.bargenson.util.faces.ControllerHelper;
//
//@RunWith(Arquillian.class)
//public class JSFLoginPageTest {
//	
//	@Deployment
//	public static WebArchive createTestArchive() {
//		return ShrinkWrap.create(WebArchive.class)
//				.addAsResource(new File("src/main/java/com/supinfo/ticketmanager/i18n/lang.properties"), "com/supinfo/ticketmanager/i18n/lang.properties")
//				.addClasses(UserControllerMock.class, ControllerHelper.class)
//				.addAsWebResource(new File("src/main/webapp/login.xhtml"), "login.xhtml")
//				.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
//				.addAsWebInfResource(new File("src/main/webapp/WEB-INF/templates/main.xhtml"), "templates/main.xhtml")
//				.addAsWebInfResource(new File("src/main/webapp/WEB-INF/faces-config.xml"), "faces-config.xml");
//	}
//	
//	
//	@Test @InitialPage("/login.jsf")
//	public void testLoginPage(JSFServerSession server, JSFClientSession client) throws IOException {
//		assertEquals("/login.xhtml", server.getCurrentViewID());
//		
//		final String username = "Developer";
//		final String password = "devdev";
//		
//		client.setValue("username", username);
//		client.setValue("password", password);
//		
//		client.click("submit");
//		
//	    assertEquals(username, server.getManagedBeanValue("#{userController.username}"));
//	    assertEquals(password, server.getManagedBeanValue("#{userController.password}"));
//	}
//
//}
