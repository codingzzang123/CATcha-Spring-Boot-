package com.ib.cat.controller.loginApi;


import com.ib.cat.service.loginApi.GoogleService;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoogleController {
    @Autowired
    GoogleService googleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberService memberService;

    @RequestMapping("/googlecallback")
    public void google(String code){
        System.out.println(code);
        String token = googleService.getToken(code);
        System.out.println(token);
        googleService.getUserInfo(token);
    }

}
