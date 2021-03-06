package com.ib.cat.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "board")
@Data
@DynamicInsert
public class Board{
    @Id
    @SequenceGenerator(name="seq", sequenceName="SEQ_BOARD", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq")
    private Integer no;
    private String name;
    private String cate;
    private String title;
    private String content;
    private Timestamp regdate;

    @ColumnDefault(value= "0")
    private int views;
    private int likes;

    @ColumnDefault(value = "default.png")
    private String imgo;

    @ColumnDefault(value = "default.png")
    private String imgs;

    @ColumnDefault(value = "0")
    private int replys;


}
