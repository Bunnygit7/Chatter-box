package com.amvb.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginRequest {
	
	@NotBlank
	@NotEmpty
	@NotNull
	private String userName;
	@NotBlank
	@NotEmpty
	@NotNull
	private String password;

}
