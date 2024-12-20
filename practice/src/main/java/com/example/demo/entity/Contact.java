package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "contacts")
@Data
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "first_name")
	private String firstName;

	private String email;
	private String phone;

	@Column(name = "zip_code")
	private String zipCode;

	private String address;

	@Column(name = "building_name")
	private String buildingName;

	@Column(name = "contact_type")
	private String contactType;

	private String body;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime creationDate;

	@Column(name = "updated_at")
	private LocalDateTime lastUpdatedDate;

	@PrePersist
	protected void onCreate() {
		creationDate = LocalDateTime.now();
		lastUpdatedDate = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		lastUpdatedDate = LocalDateTime.now();
	}
}
