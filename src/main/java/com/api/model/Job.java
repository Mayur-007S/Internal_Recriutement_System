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
@Table(name = "job")
@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Column(columnDefinition = "TEXT")
	private String description;
	private Double minExperience;
	private Double maxEperience;
	private String location;
	private String salaryRange;
	private Integer opening = 1;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.OPEN;
	
	private Boolean deleted = false;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Submission> submission;
	
	public enum Status{
		OPEN,
		CLOSED,
		HOLD
	}
}
