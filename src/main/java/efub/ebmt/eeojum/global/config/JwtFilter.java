package efub.ebmt.eeojum.global.config;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private static final String BEARER = "Bearer ";
    private final String secretKey;
    public JwtFilter(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null && token.startsWith(BEARER)) {
            try {
                token = token.replace(BEARER, "").trim();
                log.info("Extracted Token: {}", token);
                String memberId = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token)
                        .getBody()
                        .get("memberId").toString();
                log.info("Extracted MemberId: {}", memberId);

                if (memberId != null && !memberId.trim().isEmpty()) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(memberId, null, null);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                log.error("Token processing error", e);
            }
        }
        filterChain.doFilter(request, response);
    }
}