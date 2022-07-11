package com.ib.cat.service.member;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    public String authCode(){
        UUID uuid = UUID.randomUUID();
        String code = String.valueOf(uuid);
        return code;
    }
}
