package com.ib.cat.dto.main;

import lombok.Data;

import java.util.Date;

@Data
public class BoardTopDto {
    private int no;
    private String title;
    private String name;
    private Date regdate;
}
