package com.example.demo.application.config;

import com.example.demo.apis.resources.LogMessage;
import com.example.demo.application.enums.MessageType;
import com.example.demo.application.services.KafkaProducer;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private final KafkaProducer kafkaProducer;

    public RequestResponseLoggingFilter(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Wrap request/response to allow multiple reads
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        // Continue with filter chain
        filterChain.doFilter(wrappedRequest, wrappedResponse);

        // Capture request body
        String requestBody = new String(wrappedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);
        if (!requestBody.isEmpty()) {
            LogMessage requestLog = new LogMessage(requestBody, MessageType.Request, LocalDateTime.now());
            kafkaProducer.sendLog(requestLog);
        }

        // Capture response body
        String responseBody = new String(wrappedResponse.getContentAsByteArray(), StandardCharsets.UTF_8);
        if (!responseBody.isEmpty()) {
            LogMessage responseLog = new LogMessage(responseBody, MessageType.Response, LocalDateTime.now());
            kafkaProducer.sendLog(responseLog);
        }

        // Important: copy content back into real response
        wrappedResponse.copyBodyToResponse();
    }
}
