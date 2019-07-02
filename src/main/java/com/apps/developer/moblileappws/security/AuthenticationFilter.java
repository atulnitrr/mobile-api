package com.apps.developer.moblileappws.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.apps.developer.moblileappws.model.request.UserLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override public Authentication attemptAuthentication(final HttpServletRequest request,
            final HttpServletResponse response)
            throws AuthenticationException {

        try {
            final UserLoginRequest credential = new ObjectMapper().readValue(request.getInputStream(),
                    UserLoginRequest.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credential.getEmail()
                    , credential.getPassword(), Collections.emptyList()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override protected void successfulAuthentication(final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain, final Authentication authResult) throws IOException, ServletException {

        final String userName = ((User) authResult.getPrincipal()).getUsername();
        final String token = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConsts.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConsts.TOKEN_SECRET)
                .compact();

        response.addHeader(SecurityConsts.HEADER_STRING, SecurityConsts.TOKE_BEARER + token);
    }
}
