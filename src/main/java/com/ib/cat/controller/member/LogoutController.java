package com.ib.cat.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class LogoutController {

    @RequestMapping("/member/logout")
    public void logout(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/html; charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.println("<script>");
        out.println("alert('로그아웃 되었습니다.')");
        out.println("history.back()");
        out.println("</script>");
        out.flush();
    }
}
