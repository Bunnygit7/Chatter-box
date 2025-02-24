package com.amvb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "chat_users")
public class User {

	@Id
	@NotBlank
	@NotEmpty
	@NotNull
	@Column(length = 25)
	private String userName;
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min =   8,message = "password should be graterthan 8 characters")
	@Column(length = 25)
	private String password;
	@NotBlank
	@NotEmpty
	@NotNull
	@Email
	@Column(length = 25)
	private String EmailId;
	@NotBlank
	@NotEmpty
	@NotNull
	@Column(length = 25)
	private String Name;
	@NotBlank
	@NotEmpty
	@NotNull
	@Column(length = 25)
	private String DOB;
	@NotBlank
	@NotEmpty
	@NotNull
	@Column(length = 25)
	private String mobileNumber;
}
