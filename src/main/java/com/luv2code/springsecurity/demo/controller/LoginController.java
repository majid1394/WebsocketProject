package com.luv2code.springsecurity.demo.controller;

import com.luv2code.springsecurity.demo.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		// return "plain-login";
		
		return "fancy-login";
		
	}
	@GetMapping("/registerTheUser")
	public String registerTheUser(Model theModel) {
		Users theUser=new Users();
		theModel.addAttribute("user", theUser);

		// return "plain-login";

		return "register";

	}
}
