package com.supinfo.ticketmanager.entity;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 10:45
 */
public enum TicketPriority implements I18nEnum {

    TRIVIAL("ticket.priority.trivial"),
    MINOR("ticket.priority.minor"),
    MAJOR("ticket.priority.major"),
    CRITICAL("ticket.priority.critical"),
    BLOCKER("ticket.priority.blocker");

    private String bundleKey;

    TicketPriority(String bundleKey) {
        this.bundleKey = bundleKey;
    }

    public String getBundleKey() {
        return bundleKey;
    }
}
