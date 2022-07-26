package com.ib.cat.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CookieUtil {
    private String ip; /*change*/

    public boolean isCookie(HttpServletRequest request) {
        Cookie[] list = request.getCookies();
        if(list != null) {
            for(Cookie cookie:list) {
                if(cookie.getName().equals("userIp")) {
                    return true;
                }
            }
        }
        /* ip주소 가져오는 부분 */
        request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = request.getRemoteAddr();

        return false;
    }

    public void createCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("userIp",ip);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        // 일단 1시간 저장
        cookie.setMaxAge(60*60*24);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }
}
