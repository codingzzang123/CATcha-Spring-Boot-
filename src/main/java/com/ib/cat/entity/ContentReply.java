package com.ib.cat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="contentreply")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentReply {
    @Id
    @SequenceGenerator(name="seq_contentreply", sequenceName="seq_contentreply", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_contentreply")
    private int no;

    @Column(name="contentsnum")
    private int contentsNum;

    @Column(name="writer")
    private String writer; //auth.id

    @Column(name="content")
    private String content;

    @Column(name="regdate")
    private Timestamp regdate;

    @Column(name="code")
    private int code; // movie:0, tv:1

    public ContentReply(int contentsNum, String writer, String content, int code){}
}
