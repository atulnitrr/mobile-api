package com.apps.developer.moblileappws.security;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import io.jsonwebtoken.Jwts;


public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(
            final AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain chain) throws IOException, ServletException {

        final String header = request.getHeader(SecurityConsts.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityConsts.TOKE_BEARER)) {
            chain.doFilter(request, response);
            return;
        }

        final UsernamePasswordAuthenticationToken authentication = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConsts.HEADER_STRING);
        if (token != null) {
            token = token.replace(SecurityConsts.TOKE_BEARER, "");
            final String user = Jwts.parser()
                    .setSigningKey(SecurityConsts.TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }

        return null;
    }
}
