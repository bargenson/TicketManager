package com.supinfo.ticketmanager.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 10:40
 */
@Entity
public class Ticket implements Serializable {

    @Id @GeneratedValue
    private Long id;

    @NotBlank @Size(min = 4, max = 60)
    private String summary;

    @Lob
    @NotBlank
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TicketPriority priority;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @NotNull @Past
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotNull
    @ManyToOne @JoinColumn
    private ProductOwner reporter;

    @ManyToOne @JoinColumn
    private Developer developer;


    public Ticket() {

    }

    public Ticket(String summary, String description, TicketPriority priority, TicketStatus status, Date createdAt, ProductOwner reporter) {
        this.summary = summary;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.reporter = reporter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ProductOwner getReporter() {
        return reporter;
    }

    public void setReporter(ProductOwner reporter) {
        this.reporter = reporter;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
    
}
