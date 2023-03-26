package pl.kkski.watermeauth.config;

import static pl.kkski.watermeauth.model.role.RoleConstants.ADMIN;
import static pl.kkski.watermeauth.model.role.RoleConstants.CARETAKER;
import static pl.kkski.watermeauth.model.role.RoleConstants.HEAD_CARETAKER;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import pl.kkski.watermeauth.service.CustomUserDetailsService;

@Configuration
public class SecurityConfiguration {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, CustomUserDetailsService customUserDetailsService) throws Exception {
    CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider(customUserDetailsService, passwordEncoder());
    http.authenticationProvider(customAuthenticationProvider)
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/api/caretaker/**").hasAnyRole(CARETAKER, HEAD_CARETAKER, ADMIN)
            .requestMatchers("/api/headcaretaker/**").hasAnyRole(HEAD_CARETAKER, ADMIN)
            .requestMatchers("/api/admin/**").hasAnyRole(ADMIN)
        )
        .httpBasic()
        .and()
        .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // configure the CSRF token repository
        .and()
        .addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(CookieCsrfTokenRepository.withHttpOnlyFalse()), CsrfFilter.class);
    return http.build();
  }

}