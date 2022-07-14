package com.ib.cat.controller.member;

import com.ib.cat.entity.Member;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChangeController {
    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/member/change")
    public String getChange(){
        return "member/change";
    }

    @PostMapping("/member/change")
    public String postChange(@RequestParam("id") String id, String newPw){
        Member member = memberService.findById(id);
        member.setPw(passwordEncoder.encode(newPw));
        memberService.memberUpdate(member);
        return "redirect:/main";
    }
}
