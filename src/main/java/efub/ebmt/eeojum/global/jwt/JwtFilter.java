package efub.ebmt.eeojum.global.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpHeaders;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String BEARER = "Bearer";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private static String resolveToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if(authorization == null || !authorization.startsWith(BEARER)) {
            return null;
        }
        return authorization.split(" ")[1];
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);

        if(token != null && jwtAuthenticationProvider.validateToken(token)){
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication = jwtAuthenticationProvider.authenticate(token);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
            log.debug("[+] Token in SecurityContextHolder");
        }
        filterChain.doFilter(request, response);
    }
}

