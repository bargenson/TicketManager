package com.supinfo.ticketmanager.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class UnknownUserException extends RuntimeException {

    public UnknownUserException(String username, Throwable cause) {
        super("Unknown user with username: " + username + ".", cause);
    }

}
