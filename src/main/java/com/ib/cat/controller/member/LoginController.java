package com.ib.cat.controller.member;

import com.ib.cat.dto.member.MemberDto;
import com.ib.cat.entity.Member;
import com.ib.cat.service.member.FileService;
import com.ib.cat.service.member.MailService;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Time;

@Controller
public class LoginController {
    @Autowired
    FileService fileService;
    @Autowired
    MemberService memberService;
    @Autowired
    MailService mailService;

    @GetMapping("member/login")
    public String getLogin(){
        return "member/login";
    }

    @PostMapping("member/login")
    public String postLogin(
            HttpServletRequest req, @Valid MemberDto memberDto, Member member, MultipartFile file, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "member/sign";
        }
        String path = System.getProperty("user.dir")+"/src/main/resources/static/img/profile/";
        if(!file.isEmpty()) {
            String img[] = fileService.fileUpload(file, path);
            member.setImgo(img[0]);
            member.setImgs(img[1]);
        }

        mailService.emailSend(member.getEmail());
        memberService.memberInsert(member);
        return "member/login";
    }

    @PostMapping("/auth")
    public String auth(String id, String pw){
        return "rediect:/main";
    }
}
