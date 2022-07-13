package com.ib.cat.controller.auth;

import com.ib.cat.entity.Member;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    MemberService memberService;
    @GetMapping("/auth")
    public String getAuth(String id, String auth){
        Member member = memberService.findById(id);
        member.setAuth("1");
        memberService.memberUpdate(member);
        return "member/auth";
    }

    //이메일 인증 확인
    @RequestMapping(value="/member/authCheck", method = {RequestMethod.POST})
    @ResponseBody
    public int auth(String id){
        Member member = memberService.findById(id);
        if(!member.getAuth().equals("1")){
            //인증실패
            return 0;
        }else {
            //인증성공
            return 1;
        }
    }
}
