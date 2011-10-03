package com.supinfo.ticketmanager.dao.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.supinfo.ticketmanager.dao.CommentDao;
import com.supinfo.ticketmanager.entity.Comment;

@Stateless
public class JpaCommentDao implements CommentDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Comment addComment(Comment comment) {
		em.persist(comment);
		return comment;
	}

}
