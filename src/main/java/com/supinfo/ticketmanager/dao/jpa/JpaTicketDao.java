package com.supinfo.ticketmanager.dao.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.supinfo.ticketmanager.dao.TicketDao;
import com.supinfo.ticketmanager.entity.Ticket;
import com.supinfo.ticketmanager.entity.TicketStatus;
import com.supinfo.ticketmanager.exception.UnknownTicketException;

@Stateless
public class JpaTicketDao implements TicketDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Ticket addTicket(Ticket ticket) {
		em.persist(ticket);
		return ticket;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Ticket> getAllTickets() {
		return em.createQuery("SELECT t FROM Ticket t").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Ticket> getAllTickets(TicketStatus status) {
		return em.createQuery("SELECT t FROM Ticket t WHERE t.status = :status")
				.setParameter("status", status)
				.getResultList();
	}

	@Override
	public void removeAllTickets() {
		em.createQuery("DELETE FROM Ticket");
	}

	@Override
	public Ticket findTicketWithCommentsById(Long id) {
		try {
			return (Ticket) em.createQuery(
					"SELECT t FROM Ticket t LEFT JOIN FETCH t.comments WHERE t.id = :ticketId"
					)
					.setParameter("ticketId", id)
					.getSingleResult();
		} catch (NoResultException e) {
			throw new UnknownTicketException(id, e);
		}
	}

}
