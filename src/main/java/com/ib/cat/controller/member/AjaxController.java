package com.ib.cat.controller.member;

import com.ib.cat.entity.Member;
import com.ib.cat.service.member.MailService;
import com.ib.cat.service.member.MemberService;
import com.ib.cat.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
    @Autowired
    MemberService memberService;
    @Autowired
    MailService mailService;
    @Autowired
    RandomUtil randomUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value="/member/idCheck", method = {RequestMethod.POST})
    @ResponseBody
    public int idCheck(@RequestParam("id") String id) {
        return memberService.idCheck(id);
    }

    @RequestMapping(value="/member/nameCheck", method = {RequestMethod.POST})
    @ResponseBody
    public int nameCheck(@RequestParam("name") String name) {
        return memberService.nameCheck(name);
    }

    @RequestMapping(value="/member/emailCheck", method = {RequestMethod.POST})
    @ResponseBody
    public int emailCheck(@RequestParam("id") String id, @RequestParam("email") String email) {
        return memberService.emailCheck(id, email);
    }

    @RequestMapping(value="/member/sendEmailPw", method = {RequestMethod.POST})
    @ResponseBody
    public void sendPw(@RequestParam("id") String id, @RequestParam("email") String email) {
        Member member = memberService.findById(id);
        memberService.memberUpdate(member);
        String pw = randomUtil.RandomPw();
        member.setPw(passwordEncoder.encode(pw));
        memberService.memberUpdate(member);
        mailService.pwSend(email, id, pw);
    }

    @RequestMapping(value="/member/sendEmail", method = {RequestMethod.POST})
    @ResponseBody
    public void sendEmail(String id){
        if(memberService.idCheck(id) == 1){
            Member member = memberService.findById(id);
            mailService.emailSend(member.getEmail(), member.getId(), member.getAuth());
        }
    }

    @RequestMapping(value="/member/pwCheck", method = {RequestMethod.POST})
    @ResponseBody
    public int pwCheck(@RequestParam("id") String id, @RequestParam("oldPw") String oldPw) {
        if(memberService.findById(id).getPw().equals(oldPw)){
            return 1;
        }else {
            return 0;
        }
    }
}
