package com.supinfo.ticketmanager.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class UnknownTicketException extends RuntimeException {

    public UnknownTicketException(Long ticketId, Throwable cause) {
        super("Unknown ticket with id: " + ticketId + ".", cause);
    }
	
}
