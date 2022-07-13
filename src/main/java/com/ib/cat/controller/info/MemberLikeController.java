package com.ib.cat.controller.info;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberLikeController {

    @GetMapping("/memberlike/{id}")
    public String likeForm(@PathVariable String id, Model model){

        model.addAttribute("id",id);


        return "info/memberlike";
    }
}
