package com.ib.cat.dto.Info;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class ListBoardDto {
    private int no;
    private String title;
    private String cate;
    private Timestamp regdate;
}
