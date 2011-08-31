package com.supinfo.ticketmanager.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.supinfo.ticketmanager.dao.UserDao;
import com.supinfo.ticketmanager.entity.Developer;
import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.entity.User;
import com.supinfo.ticketmanager.security.UserRole;

import fr.bargenson.util.security.AbstractLoginModule;
import fr.bargenson.util.security.UserInfo;

/**
 * Created by IntelliJ IDEA.
 * User: bargenson
 * Date: 30/08/11
 * Time: 11:46
 */
@Stateless
public class UserService extends AbstractLoginModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @EJB
    private UserDao userDao;


    @Override
    public UserInfo getUserInfo(String username) throws Exception {
    	LOGGER.info("getUserInfo(" + username + ")"); // TODO
        final User user = userDao.findUserByUsername(username);
        return new UserInfo() {
            public String getUsername() {
                return user.getUsername();
            }

            public boolean checkPassword(char[] suppliedPassword) {
            	String encryptedSuppliedPassword = encryptPassword(String.valueOf(suppliedPassword));
                LOGGER.info(encryptedSuppliedPassword); // TODO
            	return user.getEncryptedPassword().equals(encryptedSuppliedPassword);
            }

            public char[] getPassword() {
                return user.getEncryptedPassword().toCharArray();
            }

            public List<String> getRoleNames() {
                List<String> roles = new ArrayList<String>();
                if(user instanceof Developer) {
                    roles.add(UserRole.DEVELOPER);
                } else if(user instanceof ProductOwner) {
                    roles.add(UserRole.PRODUCT_OWNER);
                }
                return roles;
            }
        };
    }
    
    private String encryptPassword(String password) {
        try {
            return new String(MessageDigest.getInstance("SHA-1").digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}
    
}