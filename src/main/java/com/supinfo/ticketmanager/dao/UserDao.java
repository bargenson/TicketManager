package com.supinfo.ticketmanager.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.supinfo.ticketmanager.entity.Developer;
import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.entity.User;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 13:01
 */
@Local
public interface UserDao extends Serializable {
	
	<T extends User> T addUser(T user);
    User findUserByUsername(String username);
	List<User> getAllUsers();
	void removeAllUsers();
	List<Developer> getAllDevelopers();
	List<ProductOwner> getAllProductOwners();
	
}
