package com.api.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "client")
@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String companyName;
	private String contractPerson;
	private String email;
	private String phone;
	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;
	private Boolean deleted = false;
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Job> jobs;
	
	public enum Status {
		ACTIVE,
		INACTIVE
	}

}
