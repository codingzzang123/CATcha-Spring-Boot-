package com.ib.cat.controller.main;

import com.ib.cat.entity.Member;
import com.ib.cat.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    BoardRepository boardRepository;

    @RequestMapping("t/t")
    public String test(){
        String result = "";

//        Member member =
//
//        result = "이메일" + member.getEmail()+"닉네임"+ member.getName();
        return "FF";
    }

}
