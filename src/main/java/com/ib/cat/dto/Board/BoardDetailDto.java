package com.ib.cat.dto.Board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class BoardDetailDto {
    private int no;
    private String name; //게시글 작성자
    private String cate;
    private String title;
    private String content;
    private Timestamp regdate;

    private int views;
    private int likes;
    private int replys;

    private String Imgs;
}
