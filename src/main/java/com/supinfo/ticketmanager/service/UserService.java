package com.supinfo.ticketmanager.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import com.supinfo.ticketmanager.dao.UserDao;
import com.supinfo.ticketmanager.entity.Developer;
import com.supinfo.ticketmanager.entity.ProductOwner;
import com.supinfo.ticketmanager.entity.User;
import com.supinfo.ticketmanager.service.security.Role;

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

    @EJB
    private UserDao userDao;


    @Override
    protected UserInfo getUserInfo(String username) throws Exception {
        final User user = userDao.findUserByUsername(username);
        return new UserInfo() {
            public String getUsername() {
                return user.getUsername();
            }

            public boolean checkPassword(char[] suppliedPassword) {
                return user.getEncryptedPassword().equals(encryptPassword(String.valueOf(suppliedPassword)));
            }

            public char[] getPassword() {
                return user.getEncryptedPassword().toCharArray();
            }

            public List<String> getRoleNames() {
                List<String> roles = new ArrayList<String>();
                if(user instanceof Developer) {
                    roles.add(Role.DEVELOPER.name());
                } else if(user instanceof ProductOwner) {
                    roles.add(Role.PRODUCT_OWNER.name());
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

	public void resetUsers() {
		userDao.removeAllUsers();
		userDao.addUser(
				new Developer(
						"Developer", encryptPassword("devdev"), 
						"Linus", "Torvald", 
						"linus@torvald.org", new Date()
				)
		);
		userDao.addUser(
				new ProductOwner	(
						"ProductOwner", encryptPassword("popo"), 
						"Steve", "Jobs", 
						"steve@apple.com", new Date()
				)
		);
	}
    
}
