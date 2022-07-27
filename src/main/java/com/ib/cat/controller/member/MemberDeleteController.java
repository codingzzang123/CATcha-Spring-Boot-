package com.ib.cat.controller.member;


import com.ib.cat.entity.Member;
import com.ib.cat.service.member.FileService;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MemberDeleteController {
    @Autowired
    MemberService memberService;
    @Autowired
    FileService fileService;

    @PostMapping("/member/delete")
    public String delete(String check_id, HttpServletRequest httpServletRequest){
        Member member = memberService.findById(check_id);

//        String path = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/static/img/profile/");
        String path = System.getProperty("user.dir")+"/src/main/resource/static/img/profile/";
        if(!member.getImgs().equals("default.png")){
            fileService.fileDelete(path,member.getImgs());
        }
        memberService.memberDelete(member);
        return "redirect:/logout";
    }
}
