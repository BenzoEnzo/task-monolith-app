package pl.benzo.enzo.server.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.benzo.enzo.server.api.service.basic.AccountServiceBasic;
import pl.benzo.enzo.server.util.Role;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class FilterBeforeRequest extends OncePerRequestFilter {
    private final static Logger LOGGER = LoggerFactory.getLogger(FilterBeforeRequest.class);
    private final JWT jwt;
    private final AccountServiceBasic accountServiceBasic;
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String mail = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            mail = jwt.extractUsername(token);
        }

        LOGGER.info(mail);

        if (mail != null && jwt.validateToken(token, mail)) {
            final String finalMail = mail;
            final Role role = accountServiceBasic.findAccountByMail(finalMail).getRole();
            Authentication authToken = new AbstractAuthenticationToken(null) {
                @Override
                public Object getCredentials() {
                    return role;
                }

                @Override
                public Object getPrincipal() {
                    return finalMail;
                }

                @Override
                public boolean isAuthenticated() {
                    return true;
                }
            };

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
