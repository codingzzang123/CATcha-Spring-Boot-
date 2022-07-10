package com.ib.cat.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class BoardEntity {
    @Id
    @SequenceGenerator(name="seq", sequenceName="SEQ_BOARD", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq")
    private long no;
    private String name;
    private String cate;
    private String title;
    private String Content;
    private Timestamp regDate;
    private int views;
    private int likes;
    private String imgo;
    private String imgs;
    private int replys;
}
