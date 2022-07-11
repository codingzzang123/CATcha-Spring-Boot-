package com.ib.cat.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "member")
public class Member {
    @Id
    @Column(name="marker") //MediaLike 테이블 - reference 대상키
    private String id;
    private String pw;
    private String name;
    private String email;
    @ColumnDefault("default.png")
    private String imgo;
    @ColumnDefault("default.png")
    private String imgs;
    private String auth;

    protected Member(){
    }

    @Builder
    public Member(String id, String pw, String name, String email, String imgo, String imgs, String auth){
        this.id=id;
        this.pw=pw;
        this.name=name;
        this.email=email;
        this.imgo=imgo;
        this.imgs=imgs;
        this.auth=auth;
    }

//    @OneToMany(mappedBy="marker")
//    List<Member> members = new ArrayList<>();
}


