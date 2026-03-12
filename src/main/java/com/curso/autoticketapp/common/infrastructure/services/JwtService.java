package com.curso.autoticketapp.common.infrastructure.services;

import com.curso.autoticketapp.common.domain.exception.NoValidJwtException;
import com.curso.autoticketapp.common.domain.exception.TokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret_key}")
    private String SECRET_KEY;

    @Value("${security.jwt.token_expiration}")
    private long TOKEN_EXPIRATION;

    @Value("${security.jwt.refresh_window}")
    private long REFRESH_WINDOW;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = Map.of(
                "authorities",
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList());

        return generateToken(claims, userDetails.getUsername());
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(getSignInKey())
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsMapper) {
        Claims allClaims = getAllClaims(token);
        return claimsMapper.apply(allClaims);
    }

    private Claims getAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new NoValidJwtException(e);
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsername(token);
        return username.equals(userDetails.getUsername());
    }

    public String renewToken(String token, UserDetails userDetails) {
        if (!canBeRenewed(token)) {
            throw new TokenException("Token can not be renewed");
        }

        return generateToken(userDetails);
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    private boolean canBeRenewed(String token) {
        long expiredAt = getExpirationDate(token).getTime();
        long now = System.currentTimeMillis();
        return now > expiredAt && now < expiredAt + REFRESH_WINDOW;
    }

    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private Date getExpirationDate(String token) {
        return getClaim(token, Claims::getExpiration);
    }
}
