package com.gateway.api_gateway.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Record start time
        long startTime = System.currentTimeMillis();

        // Log incoming request
        System.out.println("========================================");
        System.out.println("INCOMING REQUEST");
        System.out.println("Time      : " + LocalDateTime.now());
        System.out.println("Method    : " + request.getMethod());
        System.out.println("URL       : " + request.getRequestURI());
        System.out.println("IP Address: " + request.getRemoteAddr());
        System.out.println("========================================");

        // Pass request to next filter or controller
        filterChain.doFilter(request, response);

        // Calculate how long request took
        long duration = System.currentTimeMillis() - startTime;

        // Log outgoing response
        System.out.println("========================================");
        System.out.println("OUTGOING RESPONSE");
        System.out.println("Status    : " + response.getStatus());
        System.out.println("Duration  : " + duration + "ms");
        System.out.println("========================================");
    }
}