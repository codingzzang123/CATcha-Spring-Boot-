package com.ib.cat.controller.info;

import com.ib.cat.dto.member.Auth;
import com.ib.cat.service.info.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MemberInfoController {

    @Autowired
    InfoService infoService;

    @GetMapping("/member")
    public String infoForm(Model model, HttpSession session){
        Auth auth = (Auth) session.getAttribute("auth");

        if(auth == null)
            return "redirect:/main";


        model.addAttribute("count",infoService.infoCountDto(auth.getName(), auth.getId()));
        model.addAttribute("boards", infoService.getBoards(auth.getName()));
        model.addAttribute("reviews", infoService.getMedias(auth.getId()));
        return "info/memberInfo";
    }
}
