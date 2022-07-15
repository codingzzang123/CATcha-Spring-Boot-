package com.ib.cat.controller.info;

import com.ib.cat.dto.Info.InfoCountDto;
import com.ib.cat.dto.member.Auth;
import com.ib.cat.service.info.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

        InfoCountDto infoCountDto = infoService.infoCountDto(auth.getName());
        model.addAttribute("count",infoCountDto);
        return "info/memberInfo";
    }
}
