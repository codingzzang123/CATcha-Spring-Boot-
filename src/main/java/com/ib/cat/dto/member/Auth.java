package com.ib.cat.dto.member;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Auth {
    private String id;
    private String name;
    private String imgs;
    private Timestamp regdate;
}
