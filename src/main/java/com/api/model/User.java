package com.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "users", 
	indexes = {
			@Index(name = "idx_user_email", columnList = "Email"),
			@Index(name = "idx_user_role", columnList = "role")
			}
		)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	private String name;
	private String Email;
	private String password;
	private Role role;
	private Boolean active = true;
	private LocalDateTime dateTime = LocalDateTime.now();

	
	public enum Role {

		RECRUITER,
		CANDIDATE
	}

}


