package com.ib.cat.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "member")
public class Member {
    @Id
    private String id;
    private String pw;
    private String name;
    private String email;
    @ColumnDefault(value = "default.png")
    private String imgo;
    @ColumnDefault(value = "default.png")
    private String imgs;
    private int auth;
}
