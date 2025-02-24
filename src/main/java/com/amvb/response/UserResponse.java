package com.amvb.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	@NotBlank
	@NotEmpty
	@NotNull
	private String userNameResponse;
	@NotBlank
	@NotEmpty
	@NotNull
	@Email
	private String EmailIdResponse;
	@NotBlank
	@NotEmpty
	@NotNull
	private String NameResponse;
	@NotBlank
	@NotEmpty
	@NotNull
	private String DOBResponse;
	@NotBlank
	@NotEmpty
	@NotNull
	private String mobileNumberResponse;
}
