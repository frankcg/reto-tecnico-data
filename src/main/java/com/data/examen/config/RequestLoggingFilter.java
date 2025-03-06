package com.data.examen.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@Configuration
public class RequestLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        final ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        final ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        final long startTime = System.currentTimeMillis();

        filterChain.doFilter(requestWrapper, responseWrapper);

        final String requestBody = getStringValue(
                requestWrapper.getContentAsByteArray(),
                request.getCharacterEncoding()
        );

        final String responseBody = getStringValue(
                responseWrapper.getContentAsByteArray(),
                response.getCharacterEncoding()
        );

        log.info("[START] [{}] {} {}", request.getMethod(), request.getRequestURI(), requestBody);

        log.info("[END  ] [{}] {} {}ms {}", request.getMethod(), request.getRequestURI(), System.currentTimeMillis() - startTime, responseBody);

        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException:", e);
        }
        return "";
    }
}
