package com.supinfo.ticketmanager.web.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.supinfo.ticketmanager.entity.Comment;
import com.supinfo.ticketmanager.entity.User;
import com.supinfo.ticketmanager.service.CommentService;
import com.supinfo.ticketmanager.service.UserService;

import fr.bargenson.util.faces.ControllerHelper;

@Named
@RequestScoped
public class CommentController {

	protected static final String ADD_COMMENT_OUTCOME = "showTicket?faces-redirect=true&includeViewParams=true";
	
	@Inject
	private TicketController ticketController;
	
	@Inject
	private ControllerHelper controllerHelper;
	
	@Inject
	private CommentService commentService;
	
	@Inject
	private UserService userService;
	
	private Comment comment;

	
	public String addComment() {
		String username = controllerHelper.getUserPrincipal().getName();
		User author = userService.findUserByUsername(username);
		comment.setAuthor(author);
		commentService.addComment(comment);
		
		ticketController.setTicket(comment.getTicket());
		
		return ADD_COMMENT_OUTCOME;
	}
	
	public Comment getComment() {
		if(comment == null) {
			comment = new Comment();
		}
		return comment;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
