package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.User;
import com.example.service.UserService;



@Controller
public class MyController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/regPage")
	public String openRegPage(Model model)
	{
		model.addAttribute("user",new User());
		return "Register";
	}
	
	@PostMapping("/regForm")
	public String submitRegForm(@ModelAttribute("user")User user, Model model)
	{
		boolean status = userService.registerUser(user);
		
		if(status)
		{
			model.addAttribute("successMsg", "User registered successfully");
		}
		else
		{
			model.addAttribute("errorMsg","User not registered due to some error");
		}
		return "Register";
	}
	
	
	@GetMapping("/LoginPage")
	public String openLoginPage(Model model)
	{
		model.addAttribute("user",new User());
		return "login";
		
	}
	@PostMapping("/LoginForm")
	public String submitLoginForm(@ModelAttribute("user")User user,Model model)
	{
		User valiUser = userService.loginUser(user.getEmail(), user.getPassword());
		
		if(valiUser != null)
		{
			model.addAttribute("modelName",valiUser.getName());
			return "profile";
		}
		else
		{
			model.addAttribute("errorMsg", "Incorrect password or Username, please try again");
			return "login";
		}
	}
}
