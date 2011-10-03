package com.supinfo.ticketmanager.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;

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
    private List<Ticket> reportedTickets;

    public ProductOwner() {

    }

    public ProductOwner(String username, String encryptedPassword, String firstName, String lastName,
			String email, Date dateOfBirth) {
		super(username, encryptedPassword, firstName, lastName, email,
				dateOfBirth);
	}

	public List<Ticket> getReportedTickets() {
        return reportedTickets;
    }

    public void setReportedTickets(List<Ticket> reportedTickets) {
        this.reportedTickets = reportedTickets;
    }
    
    @Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "reportedTickets");
	}

	@Override
	@SuppressWarnings
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "reportedTickets");
	}

}
