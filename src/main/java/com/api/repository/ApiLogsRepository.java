package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.model.ApiLog;

public interface ApiLogsRepository extends JpaRepository<ApiLog, Long>{

	
}
