package com.group1.bookstore.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;
	private String password;
	private String role = "USER";
	private String fullname;

	@OneToMany(
		mappedBy = "user"
	)
	private List<Review> reviews;

	public User() {
		super();
	}

	public User(String email, String password, String role, String fullname) {

		this.email = email;
		this.password = password;
		this.role = "USER";
		this.fullname = fullname;
	}

}
