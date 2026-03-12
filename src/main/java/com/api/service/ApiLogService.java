package com.api.service;

import com.api.model.ApiLog;

public interface ApiLogService {

	public ApiLog addLogs(String transactionId, String conversationId,
            String requestURL, int status,
            String requestBody, String responseBody); 

}
