package com.ib.cat.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "board_reply")
@Data @ToString
@DynamicInsert
public class BoardReply {

    @Id
    @SequenceGenerator(name="reply", sequenceName="seq_board_reply", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="reply")
    private Integer no;
    private String writer;

    @Column(name="board_no")
    private Integer boardNo;

    private String content;
    private Timestamp regdate;
    private int ref;
    private int step;
}
