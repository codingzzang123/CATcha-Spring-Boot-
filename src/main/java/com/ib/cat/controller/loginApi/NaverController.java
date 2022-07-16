package com.ib.cat.controller.loginApi;

import com.ib.cat.entity.Member;
import com.ib.cat.service.loginApi.NaverService;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

@Controller
public class NaverController {
    @Autowired
    NaverService naverService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberService memberService;

    @RequestMapping("/navercallback")
    public String naver(String code, String state){
        String token = naverService.getAccessToken(code, state);
        HashMap<String, Object> info = naverService.getUserInfo(token);

        String no = (String.valueOf(info.get("id")));
        String id = Base64.getEncoder().encodeToString(no.getBytes(StandardCharsets.UTF_8));
        String pw = passwordEncoder.encode((CharSequence) info.get("email"));

        if(memberService.idCheck(id) == 0){
            Member member = Member.builder()
                    .id(id)
                    .pw(pw)
                    .name((String.valueOf(info.get("nickname"))))
                    .email((String.valueOf(info.get("email"))))
                    .auth("2")
                    .build();
            memberService.memberInsert(member);
        }

        Authentication naverUser = new UsernamePasswordAuthenticationToken(id, (String.valueOf(info.get("email"))));
        SecurityContextHolder.getContext().setAuthentication(naverUser);
        return "redirect:/member/apiSession";
    }
}
