package com.supinfo.ticketmanager.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;

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
    private List<Ticket> ticketsInProgress;

    public Developer() {

    }

	public Developer(String username, String encryptedPassword,
			String firstName, String lastName, String email, Date dateOfBirth) {

		super(username, encryptedPassword, firstName, lastName, email, dateOfBirth);
	}

	public List<Ticket> getTicketsInProgress() {
        return ticketsInProgress;
    }

    public void setTicketsInProgress(List<Ticket> ticketsInProgress) {
        this.ticketsInProgress = ticketsInProgress;
    }
    
    @Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "ticketsInProgress");
	}

	@Override
	@SuppressWarnings
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "ticketsInProgress");
	}
    
}
