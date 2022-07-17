package com.ib.cat.dto.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private int no;
    private int contentsNum;
    private String writer;
    private String content;
    private String title;
    private int code;
    private String img;
    private Timestamp regdate;
}
