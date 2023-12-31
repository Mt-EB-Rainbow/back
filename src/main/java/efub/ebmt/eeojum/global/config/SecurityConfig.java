package efub.ebmt.eeojum.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Value("${spring.jwt.secret-key}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/auth/signup", "/auth/signin").permitAll()
                .antMatchers(HttpMethod.GET, "/questions", "/reply/**", "/reply/question/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/**", "/questions", "/reply").authenticated()
                .antMatchers(HttpMethod.PUT, "/questions", "/reply/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/questions", "/reply/**").authenticated()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/auth/signin/complete")
                .failureUrl("/auth/signin/fail")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtFilter(secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}