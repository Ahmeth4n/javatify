package com.magnakod.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.magnakod.executor.RegisterExecutor;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
@WebFilter
@Order(1)
public class JwtFilter implements Filter {
    @Value("${spring.jwt.secret_key}")
    private String jwtSecretKey;

    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestUri = ((HttpServletRequest) request).getRequestURI();
        if (requestUri.contains("v1/api") && !requestUri.contains("v1/api/list/users")){
            logger.info("doFilter() - api endpoint called");
            String authorizationToken = httpRequest.getHeader("Authorization");

            if (authorizationToken == null){
                httpResponse.sendError(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
                httpResponse.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
                return;
            }
            try {
                Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(authorizationToken);
                logger.info("verify() - jwt verified: " + decodedJWT.getId());
            } catch (JWTVerificationException exception) {
                logger.error("verify() - jwt not verified! ");
                httpResponse.sendError(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
                httpResponse.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
                return;
            }


        }


        chain.doFilter(request,response);

    }
}
