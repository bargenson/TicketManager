package com.supinfo.ticketmanager.dao;

import java.io.Serializable;

import com.supinfo.ticketmanager.entity.Comment;

public interface CommentDao extends Serializable {

	Comment addComment(Comment comment);

}
