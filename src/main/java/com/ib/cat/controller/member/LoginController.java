package com.ib.cat.controller.member;

import com.ib.cat.dto.member.Auth;
import com.ib.cat.entity.Member;
import com.ib.cat.service.loginApi.NaverService;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Controller
public class LoginController {
    @Autowired
    MemberService memberService;
    @Autowired
    NaverService naverService;
    String kakao_client_id = "fc6944c62dae67a30e23da58dc978e9f";
    String kakao_redirect_uri = "http://localhost:8080/kakaocallback";

    String google_client_id = "1022596664094-r26vimfhq0ooql9516nctpombjqv3ilg.apps.googleusercontent.com";
    String google_redirect_uri = "http://localhost:8080/googlecallback";

    @GetMapping("member/login")
    public String getLogin(Model model, HttpSession httpSession){
        if(httpSession.getAttribute("auth") != null){
            return "redirect:/main";
        }
        String naver = naverService.getRegUrl();

        String kakao = "https://kauth.kakao.com/oauth/authorize?"
                + "client_id="+kakao_client_id+
                "&redirect_uri="+kakao_redirect_uri+
                "&response_type=code";

        String google = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id="+google_client_id
                + "&redirect_uri="+ google_redirect_uri
                + "&response_type=code"
                + "&scope=email%20profile%20openid";
                //+ "&access_type=offline";

        model.addAttribute("naver", naver);
        model.addAttribute("kakao", kakao);
        model.addAttribute("google", google);
        return "member/login";
    }

    @PostMapping("member/login")
    public String postLogin(){
        return "member/login";
    }

    @GetMapping("/member/session")
    public String getSession(HttpSession httpSession, HttpServletResponse httpServletResponse) throws IOException {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = ((User) object).getUsername();
        Member member = memberService.findById(id);
        Auth auth = new Auth();
        auth.setId(id);
        auth.setName(member.getName());
        auth.setImgs(member.getImgs());
        auth.setEmail(member.getEmail());
        auth.setRegdate(member.getRegdate());
        auth.setAuth(member.getAuth());

        httpSession.setAttribute("auth", auth);
        return "redirect:/main";
    }
    @GetMapping("/member/apiSession")
    public String getApiSession(HttpSession httpSession, Principal principal, HttpServletResponse httpServletResponse) throws IOException {
        String id = principal.getName();
        Member member = memberService.findById(id);
        Auth auth = new Auth();
        auth.setId(id);
        auth.setName(member.getName());
        auth.setImgs(member.getImgs());
        auth.setEmail(member.getEmail());
        auth.setRegdate(member.getRegdate());
        auth.setAuth(member.getAuth());

        httpSession.setAttribute("auth", auth);
        return "redirect:/main";
    }
}
