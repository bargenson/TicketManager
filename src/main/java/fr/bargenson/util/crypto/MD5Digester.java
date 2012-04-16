package fr.bargenson.util.crypto;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Digester {
	
	public String digest(String password) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(password.getBytes());
            return new String(Hex.encodeHex(digest));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

}
