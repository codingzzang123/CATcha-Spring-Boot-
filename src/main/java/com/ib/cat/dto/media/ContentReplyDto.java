package com.ib.cat.dto.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentReplyDto {

    private int no; //기본키
    private int contentsNum;
    private String writer; //auth.id
    private String content;
    private Timestamp regdate;
    @Column(columnDefinition = "default '0/")
    private int code; // movie:0, tv:1

    public ContentReplyDto(int contentsNum, String writer, String content, Timestamp regdate, int code) {
    }

    public ContentReplyDto(int contentsNum, String writer, String content, int code) {
    }

}
