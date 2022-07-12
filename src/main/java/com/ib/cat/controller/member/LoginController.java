package com.ib.cat.controller.member;

import com.ib.cat.entity.Member;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    MemberService memberService;


    @GetMapping("member/login")
    public String getLogin(){
        return "member/login";
    }

    @PostMapping("member/login")
    public String postLogin(){
        return "member/login";
    }

    @RequestMapping(value="/member/authCheck", method = {RequestMethod.POST})
    @ResponseBody
    public int auth(String id){
        Member member = memberService.findById(id);
        if(member.getAuth() != "1"){
            return 0;
        }else {
            return 1;
        }
    }
}
