package com.ib.cat.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity(name = "test")
@Data
public class Test {
    @Id
    @SequenceGenerator(name="like", sequenceName="seq_test", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="like")
    private int no;
    private String name;

    @Column(name = "contents_num")
    private int num;

    @Column(name = "poster_path")
    private String pasterPath;

    private String title;
    private String overview;
    private int code;
}

/*
*  no number primary key,
    name varchar2(100),
    contents_num varchar2(100),
    poster_path varchar2(100),
    title varchar2(100),
    overview varchar2(4000),
    type number
* */