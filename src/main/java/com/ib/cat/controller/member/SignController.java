package com.ib.cat.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignController {

    @GetMapping("/member/sign")
    public String getSign(){
        return "member/sign";
    }


}
