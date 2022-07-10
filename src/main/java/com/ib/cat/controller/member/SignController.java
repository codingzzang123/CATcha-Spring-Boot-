package com.ib.cat.controller.member;

import com.ib.cat.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignController {
    MemberService memberService;

    @GetMapping("/member/sign")
    public String getSign(){
        return "member/sign";
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
