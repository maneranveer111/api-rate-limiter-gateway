package com.gateway.api_gateway.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    // Professional logger instead of System.out.println
    private static final Logger log =
            LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        // Log incoming request
        log.info("========================================");
        log.info("INCOMING REQUEST");
        log.info("Time       : {}", LocalDateTime.now());
        log.info("Method     : {}", request.getMethod());
        log.info("URL        : {}", request.getRequestURI());
        log.info("IP Address : {}", request.getRemoteAddr());
        log.info("User Agent : {}", request.getHeader("User-Agent"));
        log.info("========================================");

        // Pass to next filter/controller
        filterChain.doFilter(request, response);

        // Calculate duration
        long duration = System.currentTimeMillis() - startTime;

        // Log outgoing response
        log.info("========================================");
        log.info("OUTGOING RESPONSE");
        log.info("Status     : {}", response.getStatus());
        log.info("Duration   : {}ms", duration);
        log.info("========================================");
    }
}
