package com.api.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.model.ApiLog;
import com.api.repository.ApiLogsRepository;
import com.api.service.ApiLogService;

@Service
public class ApiLogServiceImpl implements ApiLogService{
	
	@Autowired
	private ApiLogsRepository apiLogsRepository;
	private Logger log = LoggerFactory.getLogger(ApiLogsRepository.class);
	private List<?> response;
	
	@Override
	public ApiLog addLogs(String transactionId, String conversationId,
            String requestURL, int status,
            String requestBody, String responseBody) 
	{
		log.info("Inside API Logs Save Method");
		ApiLog apiLogs = new ApiLog();
		apiLogs.setConversationId(conversationId);
		apiLogs.setTransactionId(transactionId);
		apiLogs.setRequest(requestBody);
		if(responseBody.length() > 255) {
			responseBody = responseBody.substring(0, 255);
		}
		apiLogs.setResponse(responseBody);
		apiLogs.setNotification(String.valueOf(status));
		apiLogs.setContract(requestURL);
		
		
		return apiLogsRepository.save(apiLogs);
	}
	
	
}
