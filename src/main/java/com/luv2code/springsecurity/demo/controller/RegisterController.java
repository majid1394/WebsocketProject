package com.luv2code.springsecurity.demo.controller;

import com.luv2code.springsecurity.demo.entity.Users;
import com.luv2code.springsecurity.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/signUp")
    public String singUp(@RequestBody Users user) {
        user.setScore(0);
        registerService.save(user);
        return "redirect:/showMyLoginPage";
    }
}
