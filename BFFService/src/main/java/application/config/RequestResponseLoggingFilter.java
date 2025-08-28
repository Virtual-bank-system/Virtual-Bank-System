package application.config;

import apis.dto.LogMessage;
import application.enums.MessageType;
import application.services.impl.KafkaProducerImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private final KafkaProducerImp kafkaProducerImp;

    public RequestResponseLoggingFilter(KafkaProducerImp kafkaProducerImp) {
        this.kafkaProducerImp = kafkaProducerImp;
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
            kafkaProducerImp.sendLog(requestLog);
        }

        // Capture response body
        String responseBody = new String(wrappedResponse.getContentAsByteArray(), StandardCharsets.UTF_8);
        if (!responseBody.isEmpty()) {
            LogMessage responseLog = new LogMessage(responseBody, MessageType.Response, LocalDateTime.now());
            kafkaProducerImp.sendLog(responseLog);
        }

        // Important: copy content back into real response
        wrappedResponse.copyBodyToResponse();
    }
}