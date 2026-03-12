package com.api.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.api.service.ApiLogService;
import com.api.service.impl.ApiLogServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UiniqueFilter extends OncePerRequestFilter {

	@Autowired
	private ApiLogService logService;
	
	private static final String TRANSACTION_ID = "transaction_id";
	private static final String CONVERSATION_ID = "conversation_id";
	private Logger log = LoggerFactory.getLogger(UiniqueFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		String transactionId = request.getHeader(TRANSACTION_ID);
		if (!StringUtils.hasText(transactionId)) {
			transactionId = UUID.randomUUID().toString().replace("-", "");
		}

		String conversationId = request.getHeader(CONVERSATION_ID);
		if (!StringUtils.hasText(conversationId)) {
			conversationId = UUID.randomUUID().toString().replace("-", "");
		}

		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request, 100);
		ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

		MDC.put(CONVERSATION_ID, conversationId);
		MDC.put(TRANSACTION_ID, transactionId);
		
		try {
			long startTime = System.currentTimeMillis();
			filterChain.doFilter(wrappedRequest, wrappedResponse);
			
			String requestBody = getStringValue(wrappedRequest.getContentAsByteArray(), request.getCharacterEncoding());
			String responseBody = getStringValue(wrappedResponse.getContentAsByteArray(), response.getCharacterEncoding());
			
			logService.addLogs(transactionId, conversationId, requestBody, 0, requestBody, responseBody);
			
			log.info("URL: {}, METHOD: {}, STATUS: {}, REQUEST: {}, RESPONSE: {}",
					startTime, request.getRequestURI(), request.getMethod(),
					response.getStatus(), requestBody, responseBody);

		}finally {
			MDC.clear();
		}
	}
	

	public String getStringValue(byte[] contentAsByteArray, String CharacterEncoding) {
		try {
			return new String(contentAsByteArray, 0, contentAsByteArray.length, CharacterEncoding);
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
