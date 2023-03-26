package pl.kkski.watermeauth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

public class CsrfTokenResponseHeaderBindingFilter extends OncePerRequestFilter {

    private final CsrfTokenRepository csrfTokenRepository;

    public CsrfTokenResponseHeaderBindingFilter(CsrfTokenRepository csrfTokenRepository) {
      this.csrfTokenRepository = csrfTokenRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
      CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
      if (csrfToken != null) {
        Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
        String token = csrfToken.getToken();
        if (cookie == null || token != null && !token.equals(cookie.getValue())) {
          cookie = new Cookie("XSRF-TOKEN", token);
          cookie.setPath("/");
          response.addCookie(cookie);
        }
      }
      filterChain.doFilter(request, response);
    }
}