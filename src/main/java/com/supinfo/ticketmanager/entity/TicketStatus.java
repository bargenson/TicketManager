package com.supinfo.ticketmanager.entity;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 10:55
 */
public enum TicketStatus implements I18nEnum {

    NEW("ticket.status.new"),
    IN_PROGRESS("ticket.status.in_progress"),
    DONE("ticket.status.done");

    private String bundleKey;

    TicketStatus(String bundleKey) {
        this.bundleKey = bundleKey;
    }

    public String getBundleKey() {
        return bundleKey;
    }
    
}
