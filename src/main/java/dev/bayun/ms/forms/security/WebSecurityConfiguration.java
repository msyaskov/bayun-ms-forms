package dev.bayun.ms.forms.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Максим Яськов
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(configurer -> configurer.anyRequest().authenticated());
        http.securityContext(configurer -> configurer.securityContextRepository(new RequestAttributeSecurityContextRepository()));
        http.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()));
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    @Profile("with-principal")
    public FilterRegistrationBean<DefaultAuthorizedPrincipalFilter> defaultAuthorizedPrincipalFilterRegistrationBean() {
        AuthenticatedPrincipal principal = AuthenticatedPrincipal.builder()
                .id(UUID.fromString("a34e71c4-56a8-49a6-8389-58eda439aec4"))
                .email("ms.yaskov@gmail.com")
                .credentialsExpired(false)
                .expired(false)
                .enabled(true)
                .locked(false)
                .username("yaskov")
                .firstName("Максим")
                .lastName("Yaskov")
                .build();
        DefaultAuthorizedPrincipalFilter filter = new DefaultAuthorizedPrincipalFilter(principal);
        FilterRegistrationBean<DefaultAuthorizedPrincipalFilter> filterRegistrationBean = new FilterRegistrationBean<>(filter);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }

    public static class DefaultAuthorizedPrincipalFilter extends OncePerRequestFilter {

        private final AuthenticatedPrincipal principal;

        private final SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();

        public DefaultAuthorizedPrincipalFilter(AuthenticatedPrincipal principal) {
            this.principal = principal;
            System.out.println("!DefaultAuthorizedPrincipalFilter");
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(new TestingAuthenticationToken(principal, null, List.copyOf(principal.getAuthorities())));
            securityContextRepository.saveContext(securityContext, request, response);
            filterChain.doFilter(request, response);
        }
    }
}
