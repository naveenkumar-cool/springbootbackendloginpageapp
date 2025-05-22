package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImple implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean registerUser(User user) {
		try
		{
			userRepository.save(user);
			return true;	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public User loginUser(String email, String password) {
		
		User validUser = userRepository.findByEmail(email);
		
		if(validUser != null && validUser.getPassword().equals(password))
		{
			return validUser;
		}
		return null;
	}

}
