package com.wittysingh.app.Services;

import java.util.List;

import com.wittysingh.app.Payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto getUserById(Integer userId);
	UserDto updateUser(UserDto user, Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
