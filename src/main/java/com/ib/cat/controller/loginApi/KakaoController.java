package com.ib.cat.controller.loginApi;

import com.ib.cat.entity.Member;
import com.ib.cat.service.loginApi.KakaoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
@Controller
public class KakaoController {
    KakaoService kakaoService = new KakaoService();

    @RequestMapping(value = "/kakaocallback")
    public String file(String code) {
        String token = kakaoService.getAccessToken(code);
        HashMap<String, Object> info = kakaoService.getUserInfo(token);

        Member member = Member.builder()
                .id((String) info.get("id"))
                .build();
        System.out.println(info.get("id"));
        System.out.println(info.get("nickname"));
        System.out.println(info.get("email"));
        System.out.println(info.get("profile_image"));
        return "redirect:/member/login";
    }
}
