package com.ib.cat.controller.member;


import com.ib.cat.entity.Member;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberDeleteController {
    @Autowired
    MemberService memberService;

    @PostMapping("/member/delete")
    public String delete(String check_id){
        Member member = memberService.findById(check_id);
        memberService.memberDelete(member);
        return "redirect:/main";
    }
}
