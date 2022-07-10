package com.ib.cat.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("member/login")
    public String getLogin(){
        return "member/login";
    }

    @PostMapping("member/login")
    public String postLogin(HttpServletRequest req){
        return "member/login";
    }
}
