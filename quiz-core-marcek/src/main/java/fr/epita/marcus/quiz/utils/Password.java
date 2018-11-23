package fr.epita.marcus.quiz.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <h3>Password Utils Class</h3>
 * 
 * Creates SHA-256 hash of Password String
 * 
 * @author denyoe
 * @version 1
 */
public class Password {
	
	private static MessageDigest digest;
	
	public static String encode(String originalString) throws NoSuchAlgorithmException {
		digest = MessageDigest.getInstance("SHA-256");
		
		byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
		
		return bytesToHex(encodedhash);
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    
	    for (int i = 0; i < hash.length; i++) {
	    	String hex = Integer.toHexString(0xff & hash[i]);
	    	if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    
	    return hexString.toString();
	}
	
}