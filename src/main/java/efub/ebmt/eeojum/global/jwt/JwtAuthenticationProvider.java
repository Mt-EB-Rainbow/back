package efub.ebmt.eeojum.global.jwt;

import efub.ebmt.eeojum.domain.member.dto.response.JwtResponseDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtAuthenticationProvider {

    private static final Long ACCESS_TOKEN_VALID_TIME = Duration.ofMinutes(30).toMillis();
    private static final Long REFRESH_TOKEN_VALID_TIME = Duration.ofDays(14).toMillis();
    private final SecretKey secretKey;

    public JwtAuthenticationProvider(@Value("${jwt.secret}") String secret){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtResponseDto generateJwt(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Claims claims = Jwts.claims()
                .setSubject(authentication.getName());

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .claim("auth", authorities)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(secretKey)
                .compact();

        return new JwtResponseDto(accessToken, refreshToken);
    }

    public Authentication authenticate(String token){
        // 토큰 복호화
        // Claim: 토큰에 담는 데이터 한 조각(Key-Value 쌍)
        Claims claims = parseClaims(token);

        if(claims.get("auth") == null){
            throw new SecurityException("Unauthorized JWT Token");
        }

        Collection<SimpleGrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        log.debug("User principal: {}", principal.getUsername());
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    private Claims parseClaims(String token){
        try{
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e){
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }
        return false;
    }
}

