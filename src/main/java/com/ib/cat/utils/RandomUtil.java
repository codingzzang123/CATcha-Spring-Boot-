package com.ib.cat.utils;

import org.springframework.stereotype.Component;

@Component
public class RandomUtil {

    public String RandomPw(){
        String pswd = "";
        StringBuffer sb = new StringBuffer();
        StringBuffer sc = new StringBuffer("!@#$%^&*");
        sb.append((char)((Math.random() * 26)+65));
        for( int i = 0; i<3; i++) {
            sb.append((char) ((Math.random() * 26) + 65));
        }
        for( int i = 0; i<4; i++) {
            sb.append((char)((Math.random() * 26)+97));
        }
        for( int i = 0; i<2; i++) {
            sb.append((char)((Math.random() * 10)+48));
        }
        sb.setCharAt(((int)(Math.random()*3)+1), sc.charAt((int)(Math.random()*sc.length()-1)));
        sb.setCharAt(((int)(Math.random()*4)+4), sc.charAt((int)(Math.random()*sc.length()-1)));
        pswd = sb.toString();
        return pswd;
    }
}
