package com.viki.api.security.filters;

import com.viki.api.security.services.OpaAuthorizationService;
import com.viki.api.security.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class AppAuthorizationFilter extends OncePerRequestFilter {

    private final OpaAuthorizationService opaAuthorizationService;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // JWT
        String jwt = extractJwt(request);
        if (!jwtUtils.validateJwtToken(jwt)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            return;
        }

        String role = jwtUtils.getRoleFromJwtToken(jwt);
        String sub = jwtUtils.getSubjectFromJwtToken(jwt);
        String method = request.getMethod();
        String path = request.getRequestURI();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        // Set the authentication in the SecurityContext
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(sub, null, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean isAuthorized = opaAuthorizationService.isAuthorized(method, path, List.of(role));

        if (!isAuthorized) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
