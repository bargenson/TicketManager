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
 * Time: 11:25
 */
@Entity
@DiscriminatorValue("PRODUCT_OWNER")
public class ProductOwner extends User {

    @OneToMany
    private List<Ticket> tickets;

    public ProductOwner() {

    }

    public ProductOwner(String username, String password,
			String passwordConfirmation, String firstName, String lastName,
			String email, Date dateOfBirth) {
		super(username, password, passwordConfirmation, firstName, lastName, email,
				dateOfBirth);
	}

	public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
