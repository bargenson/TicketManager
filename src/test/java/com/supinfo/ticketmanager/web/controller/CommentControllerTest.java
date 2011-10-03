package com.supinfo.ticketmanager.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.supinfo.ticketmanager.entity.Comment;
import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketPriority;
import com.supinfo.ticketmanager.entity.TicketStatus;
import com.supinfo.ticketmanager.entity.User;
import com.supinfo.ticketmanager.service.CommentService;
import com.supinfo.ticketmanager.service.UserService;

import fr.bargenson.util.faces.ControllerHelper;
import fr.bargenson.util.test.ReflectionHelper;

public class CommentControllerTest {
	
	private CommentController commentController;
	
	private Ticket ticket;
	
	private Comment initialComment;
	private Comment commentToAdd;
	private Comment addedComment;

	private User author;
	
	
	@Before
	public void setUp() {
		commentController = new CommentController();
		
		author = new ProductOwner(
				"username", "encryptedPassword", "firstName", 
				"lastName", "email@email.fr", new Date()
		);
		author.setId(1L);
		
		ticket = new Ticket(
				"summary", "description", TicketPriority.CRITICAL, 
				TicketStatus.NEW, new Date(), (ProductOwner) author
				);
		
		initialComment = new Comment("Blabla", ticket, null, null);
		commentToAdd = new Comment(initialComment.getContent(), ticket, null, null);
		addedComment = new Comment(commentToAdd.getContent(), ticket, new Date(), author);
	}
	
	@Test
	public void testAddComment() throws Exception {
		ControllerHelper controllerHelperMock = mock(ControllerHelper.class);
		Principal principalMock = mock(Principal.class);
		when(principalMock.getName()).thenReturn(author.getUsername());
		when(controllerHelperMock.getUserPrincipal()).thenReturn(principalMock);
		injectControllerHelper(controllerHelperMock);
		
		UserService userService = mock(UserService.class);
		when(userService.findUserByUsername(author.getUsername())).thenReturn(author);
		injectUserService(userService);
		
		CommentService commentService = mock(CommentService.class);
		when(commentService.addComment(commentToAdd)).thenReturn(addedComment);
		injectCommentService(commentService);
		
		TicketController ticketController = mock(TicketController.class);
		injectTicketController(ticketController);
		
		commentController.setComment(initialComment);
		
		String outcome = commentController.addComment();
		assertEquals(CommentController.ADD_COMMENT_OUTCOME, outcome);
	}

	private void injectTicketController(TicketController ticketController)
		throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
			
		ReflectionHelper.inject(commentController, "ticketController", ticketController);
	}

	private void injectUserService(UserService userService) 
			throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
		
		ReflectionHelper.inject(commentController, "userService", userService);
	}
	
	private void injectCommentService(CommentService commentService) 
			throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
		
		ReflectionHelper.inject(commentController, "commentService", commentService);
	}

	private void injectControllerHelper(ControllerHelper controllerHelper)
			throws IllegalAccessException, NoSuchFieldException {
		
		ReflectionHelper.inject(commentController, "controllerHelper", controllerHelper);
	}

}
