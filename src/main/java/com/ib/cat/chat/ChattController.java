package com.ib.cat.chat;

import com.ib.cat.dto.member.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
public class ChattController {
    @RequestMapping("/chat")
    public String chattForm(HttpSession session, Model model) {

        Auth auth = (Auth) session.getAttribute("auth");
        if(auth == null)
            return "redirect:/main";
        else
            return "main/chatting";

    }
}
