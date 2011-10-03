package com.supinfo.ticketmanager.service;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.supinfo.ticketmanager.dao.CommentDao;
import com.supinfo.ticketmanager.entity.Comment;

@Stateless
public class CommentService implements Serializable {

	@Inject
	private CommentDao commentDao;
	
	public Comment addComment(Comment comment) {
		comment.setCreatedAt(new Date());
		return commentDao.addComment(comment);
	}

}
