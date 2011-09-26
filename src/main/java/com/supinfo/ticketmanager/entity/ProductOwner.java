package com.supinfo.ticketmanager.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    public ProductOwner(String username, String encryptedPassword, String firstName, String lastName,
			String email, Date dateOfBirth) {
		super(username, encryptedPassword, firstName, lastName, email,
				dateOfBirth);
	}

	public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    @Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "tickets");
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "tickets");
	}

}
