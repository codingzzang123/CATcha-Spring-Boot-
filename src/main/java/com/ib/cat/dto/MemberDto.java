package com.ib.cat.dto;

import lombok.Data;

@Data
public class MemberDto {
    private String id;
    private String pw;
    private String name;
    private String email;
    private String imgo;
    private String imgs;
    private int auth;
}
