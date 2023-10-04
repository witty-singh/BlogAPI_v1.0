package com.wittysingh.app.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min=4, message="Username must be of minimum 4 characters !!")
	private String name;
	
	@NotEmpty
	@Size(min=3, max=10, message="Password must be between 3 to 10 characters")
	private String password;
	
	@Email(message="Email address is not valid !!")
	private String email;
	
	@NotEmpty
	private String about;
}
