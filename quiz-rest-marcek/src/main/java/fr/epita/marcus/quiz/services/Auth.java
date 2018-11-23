package fr.epita.marcus.quiz.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Auth {
	
	public static String jwt() {
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    String token = JWT.create()
						        .withIssuer("auth0")
						        .sign(algorithm);
		    return token;
		} catch (JWTCreationException exception){
		    //Invalid Signing configuration / Couldn't convert Claims.
			return null;
		}
	}
	
	public static Boolean verify(String token) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("auth0")
		        .build(); //Reusable verifier instance
		    @SuppressWarnings("unused")
			DecodedJWT jwt = verifier.verify(token);
		    return true;
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
			return false;
		}
	}

}
