package com.ib.cat.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="like_content")
public class Like {
    @Id
    @SequenceGenerator(name="seq", sequenceName="seq_like_content", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private int no;

//    @ManyToOne
    @Column(name="name")
    private String name; //auth.id

    @Column(name="contents_num")
    private int contentsNum; //api - json반환값, board - 게시글 번호

    @Column(name="poster_path")
    private String posterPath; //member - image

    @Column(name="title")
    private String title;

    @Column(name="overview")
    private String overview; //board-content

    @Column(name="code")
    private int code;

    @Builder
    public Like(String name, int contentsNum, String title, String overview, int code) {
        this.name = name;
        this.contentsNum = contentsNum;
        this.title = title;
        this.overview = overview;
        this.code = code;
    }

    public Like(){}
}
