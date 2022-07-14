package com.ib.cat.controller.member;

import com.ib.cat.entity.Member;
import com.ib.cat.service.member.MailService;
import com.ib.cat.service.member.MemberService;
import com.ib.cat.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PassWordSearch {
    @Autowired
    MemberService memberService;
    @Autowired
    MailService mailService;
    @Autowired
    RandomUtil randomUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("member/search")
    public String getPwSearch(){
        return "member/search";
    }

    @RequestMapping(value="/member/sendEmailPw", method = {RequestMethod.POST})
    @ResponseBody
    public void emailCheck(@RequestParam("id") String id, @RequestParam("email") String email) {
        Member member = memberService.findById(id);
        memberService.memberUpdate(member);
        String pw = randomUtil.RandomPw();
        member.setPw(passwordEncoder.encode(pw));
        memberService.memberUpdate(member);
        mailService.pwSend(email, id, pw);
    }
}
