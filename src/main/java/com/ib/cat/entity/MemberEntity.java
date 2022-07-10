package com.ib.cat.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "member")
public class MemberEntity {
    @Id
    private String id;
    private String pw;
    private String name;
    private String email;
    private String imgo;
    private String imgs;
    private int auth;
}
