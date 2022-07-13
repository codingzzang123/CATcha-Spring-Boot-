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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignController {
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
    public String getSign(){
        return "member/sign";
    }

    @PostMapping("/member/insert")
    public String postSing(
            HttpServletRequest req, @Valid MemberDto memberDto, Member member, MultipartFile file, BindingResult bindingResult){
        String[] img = null;
        if(bindingResult.hasErrors()){
            return "member/sign";
        }

        String path = System.getProperty("user.dir")+"/src/main/resources/static/img/profile/";
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
}
