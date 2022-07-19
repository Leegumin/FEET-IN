package com.example.backend.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
public class LoginRequest {
	@NotBlank
  private String username;

	@NotBlank
	private String password;

}
