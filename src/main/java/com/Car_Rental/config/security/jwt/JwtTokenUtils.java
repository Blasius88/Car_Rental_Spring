package com.Car_Rental.config.security.jwt;

import com.Car_Rental.config.security.TokenConfiguration;
import com.Car_Rental.controller.requests.authentication.UserPrincipal;
import com.Car_Rental.controller.requests.user.UserCreateRequest;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.jsonwebtoken.Claims.SUBJECT;
import static java.util.Calendar.MILLISECOND;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenUtils {

    private static final String CREATED = "created";
    private static final String ROLE = "role";

    private final TokenConfiguration tokenConfig;

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Map<String, Object> claims = generateClaims(userPrincipal);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, tokenConfig.getSshSecret())
                .compact();
    }

    public String generateConfirmationToken(UserCreateRequest request) {
        return Jwts.builder()
                .setSubject(request.getLogin())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, tokenConfig.getSshSecret())
                .compact();
    }

    private Date generateExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(MILLISECOND, Integer.parseInt(tokenConfig.getExpirationTime()));
        return calendar.getTime();
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims =
                    Jwts.parser().setSigningKey(tokenConfig.getSshSecret()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Map<String, Object> generateClaims(UserPrincipal userPrincipal) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SUBJECT, userPrincipal.getUsername());
        return claims;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(tokenConfig.getSshSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
