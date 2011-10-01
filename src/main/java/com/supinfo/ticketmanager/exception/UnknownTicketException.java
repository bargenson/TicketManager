package com.supinfo.ticketmanager.exception;

public class UnknownTicketException extends RuntimeException {

	public UnknownTicketException(Long ticketId) {
        this(ticketId, null);
    }

    public UnknownTicketException(Long ticketId, Throwable cause) {
        super("Unknown ticket with id: " + ticketId + ".", cause);
    }
	
}
