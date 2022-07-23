package com.ib.cat.dto.Board;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardCommentDto {
    private Integer no;
    private String writer;
    private Integer boardNo;
    private String content;
    private Timestamp regdate;
    private int ref;
    private int step;
    private String imgs;


}
