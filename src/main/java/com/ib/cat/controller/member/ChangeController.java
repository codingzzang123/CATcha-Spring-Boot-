package com.ib.cat.controller.member;

import com.ib.cat.entity.Member;
import com.ib.cat.service.member.FileService;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ChangeController {
    @Autowired
    MemberService memberService;
    @Autowired
    FileService fileService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/member/change")
    public String change(MultipartFile file,String oldPw, String newPw, String id){
        Member member = memberService.findById(id);
        String path = System.getProperty("user.dir")+"/src/main/resources/static/img/profile/";
        if(!file.isEmpty()){
            fileService.fileDelete(path,member.getImgs());
            String[] img = fileService.fileUpload(file, path);
            member.setImgo(img[0]);
            member.setImgs(img[1]);
        }

        if(passwordEncoder.matches(oldPw, newPw)){
            member.setPw(passwordEncoder.encode(newPw));
        }

        memberService.memberUpdate(member);

        return "redirect:/main";
    }
}
