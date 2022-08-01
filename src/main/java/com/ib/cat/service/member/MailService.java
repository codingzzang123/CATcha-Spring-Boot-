package com.ib.cat.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class MailService {
    String serverIp = InetAddress.getLocalHost().getHostAddress();
    String url = "http://"+ serverIp+":8080/auth";
    @Autowired
    private JavaMailSenderImpl mailSender;

    public MailService() throws UnknownHostException {
    }

    public void test(){
        System.out.println(url);
    }

    public void emailSend(String email, String id, String auth) {
        String setFrom = "catchaservice@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력
        String toMail = email;
        String title = "회원 가입 인증 이메일 입니다.";
        String content = "Catcha에 가입 해주셔서 감사합니다. 아래 링크로 이동하시면 이메일 인증이 완료 됩니다. " +
                url+"?id="+id+"&auth="+auth;
        mailSend(setFrom, toMail, title, content);
    }

    public void pwSend(String email, String id, String pw) {
        String setFrom = "catchaservice@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력
        String toMail = email;
        String title = "비밀번호 변경 이메일 입니다.";
        String content = id+"님의 비밀번호가 변경되었습니다. 변경된 비밀번호는 "+pw+" 입니다.";
        mailSend(setFrom, toMail, title, content);
    }

    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        // true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            // true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
            helper.setText(content);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
