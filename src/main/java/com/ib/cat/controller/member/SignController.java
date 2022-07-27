package com.ib.cat.controller.member;

import com.ib.cat.dto.member.MemberDto;
import com.ib.cat.entity.Member;
import com.ib.cat.service.member.AuthService;
import com.ib.cat.service.member.FileService;
import com.ib.cat.service.member.MailService;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class

SignController {
    @Autowired
    MemberService memberService;
    @Autowired
    FileService fileService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MailService mailService;
    @Autowired
    AuthService authService;
    @GetMapping("/member/sign")
    public String getSign(HttpSession httpSession){
        if(httpSession.getAttribute("auth") != null){
            return "redirect:/main";
        }
        return "member/sign";
    }

    @PostMapping("/member/insert")
    public String postSign(@Valid MemberDto memberDto, BindingResult bindingResult, Member member, MultipartFile file, HttpServletRequest httpServletRequest){
        String[] img = null;

        if(bindingResult.hasErrors()){
            return "member/sign";
        }
//        String path = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/static/img/profile/");
        String path = System.getProperty("user.dir")+"/src/main/resource/static/img/profile/";
        if(!file.isEmpty()) {
            img = fileService.fileUpload(file, path);
            member.setImgo(img[0]);
            member.setImgs(img[1]);
        }
        member.setAuth(authService.authCode());
        member.setPw(passwordEncoder.encode(member.getPw()));
        mailService.emailSend(member.getEmail(), member.getId(), member.getAuth());
        memberService.memberInsert(member);
        return "redirect:/member/login";
    }
}
