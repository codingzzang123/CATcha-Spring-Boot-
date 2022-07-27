package com.ib.cat.controller.member;

import com.ib.cat.dto.member.Auth;
import com.ib.cat.entity.Member;
import com.ib.cat.service.member.FileService;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ChangeController {
    @Autowired
    MemberService memberService;
    @Autowired
    FileService fileService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/member/change")
    public String change(MultipartFile profile, String oldPw, String newPw, String id, HttpSession httpSession, HttpServletRequest httpServletRequest){
        Member member = memberService.findById(id);

//        String path = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/static/img/profile/");
        String path = System.getProperty("user.dir")+"/src/main/resource/static/img/profile/";
        if(!profile.isEmpty()){
            if(!member.getImgs().equals("default.png")){
                fileService.fileDelete(path,member.getImgs());
            }
            String[] img = fileService.fileUpload(profile, path);
            member.setImgo(img[0]);
            member.setImgs(img[1]);
        }

        if(passwordEncoder.matches(oldPw, newPw)){
            member.setPw(passwordEncoder.encode(newPw));
        }

        memberService.memberUpdate(member);
        Auth auth = (Auth) httpSession.getAttribute("auth");
        if(auth.getAuth().equals("1")){
            return "redirect:/member/session";
        }else {
            return "redirect:/member/apiSession";
        }
    }

}
