package com.supinfo.ticketmanager.dao.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.supinfo.ticketmanager.dao.UserDao;
import com.supinfo.ticketmanager.entity.Developer;
import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.entity.User;
import com.supinfo.ticketmanager.exception.UnknownUserException;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 11:49
 */
@Stateless
public class JpaUserDao implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public User findUserByUsername(String username) {
        try {
            return (User) em.createQuery("SELECT u FROM User u WHERE u.username = :username")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new UnknownUserException(username, e);
        }
    }

	@Override
	public <T extends User> T addUser(T user) {
		em.persist(user);
		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return em.createQuery("SELECT u FROM User u").getResultList();
	}

	@Override
	public void removeAllUsers() {
		em.createQuery("DELETE FROM User").executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Developer> getAllDevelopers() {
		return em.createQuery("SELECT d FROM Developer d").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ProductOwner> getAllProductOwners() {
		return em.createQuery("SELECT p FROM ProductOwner p").getResultList();
	}

}
