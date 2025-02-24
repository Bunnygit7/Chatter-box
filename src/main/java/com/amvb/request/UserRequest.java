package com.amvb.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserRequest {
	
	@NotBlank
	@NotEmpty
	@NotNull
	private String userNameRequest;
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min =   8,message = "password should be graterthan 8 characters")
	private String passwordRequest;
	@NotBlank
	@NotEmpty
	@NotNull
	@Email
	private String EmailIdRequest;
	@NotBlank
	@NotEmpty
	@NotNull
	private String NameRequest;
	@NotBlank
	@NotEmpty
	@NotNull
	private String DOBRequest;
	@NotBlank
	@NotEmpty
	@NotNull
	private String mobileNumberRequest;
	
}
