package com.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interview")
@Entity
public class Interviews {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "submission_id")
	private Submission submission;
	
	private Integer roundNumber;
	private String interviewerName;
	
	@Enumerated(EnumType.STRING)
	private Mode mode = Mode.ONLINE;
	
	private LocalDateTime interviewDate;
	
	@Column(columnDefinition = "TEXT")
	private String feedback;
	
	@Enumerated(EnumType.STRING)
	private Result result = Result.PENDING;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	public enum Mode{
		ONLINE,
		OFFLINE
	}
	
	public enum Result{
		PENDING,
		PASS,
		FAIL
	}
}
