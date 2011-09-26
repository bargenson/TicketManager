package fr.bargenson.util.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Digester {
	
	public String digest(String password) {
        try {
            return new String(MessageDigest.getInstance("MD5").digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

}
