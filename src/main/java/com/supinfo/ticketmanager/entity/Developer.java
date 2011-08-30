package com.supinfo.ticketmanager.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 11:22
 */
@Entity
@DiscriminatorValue("DEVELOPER")
public class Developer extends User {

    @OneToMany
    private List<Ticket> tickets;

    public Developer() {

    }

    public Developer(String username, String encryptedPassword, String firstName, String lastName, String email, Date dateOfBirth) {
        super(username, encryptedPassword, firstName, lastName, email, dateOfBirth);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
}
