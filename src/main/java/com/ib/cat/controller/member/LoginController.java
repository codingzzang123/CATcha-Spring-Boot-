package com.ib.cat.controller.member;

import com.ib.cat.dto.member.Auth;
import com.ib.cat.entity.Member;
import com.ib.cat.service.member.MailService;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    MemberService memberService;
    @Autowired
    MailService mailService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("member/login")
    public String getLogin(){
        return "member/login";
    }

    @PostMapping("member/login")
    public String postLogin(){
        return "member/login";
    }

    @GetMapping("/member/session")
    public String getSession(HttpSession httpSession){
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = ((User) object).getUsername();
        Member member = memberService.findById(id);
        Auth auth = new Auth();
        auth.setId(id);
        auth.setName(member.getName());
        auth.setImgs(member.getImgs());
        httpSession.setAttribute("auth", auth);
        return "redirect:/main";
    }
    //이메일 전송
    @RequestMapping(value="/member/sendEmail", method = {RequestMethod.POST})
    @ResponseBody
    public void sendEmail(String id){
        if(memberService.idCheck(id) == 1){
        Member member = memberService.findById(id);
        mailService.emailSend(member.getEmail(), member.getId(), member.getAuth());
        }
    }

}
