package com.ib.cat.dto.Info;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class ListReplyDto {
    private int no;
    private String title;
    private String cate;
    private String content;
    private Timestamp regdate;
}
