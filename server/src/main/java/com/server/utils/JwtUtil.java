package com.server.utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;


/** @noinspection ALL */
//marks this class as spring boot component
@Component
public class JwtUtil {


    private Dotenv dotenv = Dotenv.load();
    private final String SECRET_KEY = dotenv.get("JWT_SECRET_KEY");
    // This function generates a JWT token
    // using the username as the subject of the claim
    public String generateToken(String username){
        return Jwts
                .builder() // new jwt builder instance
                .setSubject(username) // set the subclaim to the username
                .setIssuedAt(new Date()) // sets the issued at data to the current date
                .setExpiration(new Date(System.currentTimeMillis()  + 1000 * 60 * 60 * 10)) //token expires in 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // signs the token with the HMAC SHA-256 algorithm and the secret key
                .compact(); // compacts jwt into string form
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
    }


    public boolean isTokenValid(String token,String username){
        String extractedUsername = extractUsername(token);
        if(extractedUsername.equals(username) && !isTokenExpired(token)) {
            System.out.println("This is valid");
            return true;
        } else {
            System.out.println("Not vAlid fam");
            return  false;
        }
    }

    private boolean isTokenExpired(String token){
        return Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody() // after getBody is where we get the data we want,e.g. exp date,subject and other custom claims added
                .getExpiration()
                .before(new Date());
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }
}
