package com.wittysingh.app.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittysingh.app.Entities.User;
import com.wittysingh.app.Exceptions.ResourceNotFoundException;
import com.wittysingh.app.Payloads.UserDto;
import com.wittysingh.app.Repositories.UserRepo;
import com.wittysingh.app.Services.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.DtoToUser(userDto);
		User savedUser = userRepo.save(user);
		return this.UserToDto(savedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		return this.UserToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		User updatedUser=userRepo.save(user);
		UserDto userDto1 = UserToDto(updatedUser);
		return userDto1;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		userRepo.delete(user);
		
	}
	
	private User DtoToUser(UserDto userdto) {
		User user= modelMapper.map(userdto, User.class);
//		user.setAbout(userdto.getAbout());
//		user.setEmail(userdto.getEmail());
//		user.setId(userdto.getId());
//		user.setName(userdto.getName());
//		user.setPassword(userdto.getPassword());
		return user;
	}
	
	private UserDto UserToDto(User user) {
		UserDto userDto=modelMapper.map(user, UserDto.class);
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
		return userDto;
	}

}
