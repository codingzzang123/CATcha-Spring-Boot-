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

    @ManyToOne
    @JoinColumn(name="marker")
    private Member member; //member.id - foreign key

    @Column(name="contents_num")
    private int contentsNum; //api - json반환값

    @Builder
    public MediaLike(Member member, int contentsNum) {
        this.member = member;
        this.contentsNum = contentsNum;
    }

    public MediaLike(){}
}
