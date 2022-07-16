package com.ib.cat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="content_reply")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ContentReply {
    @Id
    @SequenceGenerator(name="seq_content_reply", sequenceName="seq_content_reply", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_content_reply")
    private int no;

    @Column(name="title")
    private String title;

    @Column(name="contents_num")
    private int contentsNum;

    @Column(name="writer")
    private String writer; //auth.NAME

    @Column(name="content")
    private String content;

    @Column(name="regdate")
    private Timestamp regdate;

    @Column(name="code")
    private int code; // movie:0, tv:1

    private String img;

//    public ContentReply(int contentsNum, String writer, String content,String title, int code){}
}
