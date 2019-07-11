package com.zzp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {

    @RequestMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal, Model model){
    	System.out.println("========user=======>>");
        model.addAttribute("username", principal.getName());
        return "user/user";
    }

}
