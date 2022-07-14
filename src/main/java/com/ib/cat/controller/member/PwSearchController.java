package com.ib.cat.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PwSearchController {
    @GetMapping("member/search")
    public String getPwSearch(){
        return "member/search";
    }

}
