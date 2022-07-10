package com.ib.cat.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @PostMapping("/auth")
    public String auth(String id, String pw){

        return "rediect:/main";
    }
}
