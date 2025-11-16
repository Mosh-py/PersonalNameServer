package com.moshood.core.model;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity

@Table(name="mapped")
public class Mapped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "givenName")
	private String givenName;
	private String URL;
	@Column(name = "createdAt")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	
	
	
}
