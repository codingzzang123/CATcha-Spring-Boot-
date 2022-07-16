package com.ib.cat.dto.media;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReviewDto {
    private int contentsNum;
    private String writer;
    private String content;
    private String title;
    private int code;
    private String img;

}
