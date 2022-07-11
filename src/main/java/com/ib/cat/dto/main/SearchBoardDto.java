package com.ib.cat.dto.main;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SearchBoardDto {
    private int no;
    private String title;
    private String content;
    private String name;
    private Date regdate;
}
