package com.api.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "submission")
@Entity
public class Submission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.SUBMITED;
	
	private LocalDateTime submitedDate = LocalDateTime.now();
	private LocalDateTime updateDate = LocalDateTime.now();
	
	@Column(columnDefinition = "TEXT")
	private String notes;
	
	@OneToMany(mappedBy = "submission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Interviews> interviews;
	
	
	public enum Status{
		SUBMITED,
		SHORTLISTED,
		INTERVIEW,
		SELECTED,
		OFFERED,
		JOINED,
		REJECTED,
		DROPPED
	}
}
