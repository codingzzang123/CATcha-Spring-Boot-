package com.ib.cat.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Data
@Entity(name="like_content")
public class MediaLike {
    @Id
    @SequenceGenerator(name="seq", sequenceName="seq_like_content", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private int no;

//    @ManyToOne
    @Column(name="id")
    private String id; //member.id - foreign key

    @Column(name="contents_num")
    private int contentsNum; //api - json반환값

    @Builder
    public MediaLike(String id, int contentsNum) {
        this.id = id;
        this.contentsNum = contentsNum;
    }

    public MediaLike(){}
}
