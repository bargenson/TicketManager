package com.supinfo.ticketmanager.exception;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 12:02
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String username, String password) {
        this(username, password, null);
    }

    public AuthenticationException(String username, String password, Throwable cause) {
        super("Authentication failure with username:" + username + " and password:" + password + ".", cause);
    }

}
