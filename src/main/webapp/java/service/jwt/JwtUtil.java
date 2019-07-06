package service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtil {
    private static final String KEY = "HandsomeDong";

    public String createToken(String userId){
        String token = null;

        Date expiresAt = new Date(System.currentTimeMillis() + 24L * 60L * 3600L * 1000L);

        token = JWT.create()
                .withIssuer("auth0")
                .withClaim("userId", userId)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(KEY.getBytes()));
        return token;
    }

    public DecodedJWT parseToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY.getBytes()))
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);

        return jwt;
    }

    public String getUserIdByToken(String token) throws JWTVerificationException {
        DecodedJWT jwt = parseToken(token);
        String userId = jwt.getClaim("userId").asString();
        return userId;
    }
}
