package com.supinfo.ticketmanager.exception;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 11:54
 */
public class UnknownUserException extends RuntimeException {

    public UnknownUserException(String username) {
        this(username, null);
    }

    public UnknownUserException(String username, Throwable cause) {
        super("Unknown user with username: " + username + ".", cause);
    }

}
