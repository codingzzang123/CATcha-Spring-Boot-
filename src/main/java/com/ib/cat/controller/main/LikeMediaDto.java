package com.ib.cat.controller.main;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeMediaDto {
    private String name;
    private int num;
    private String poster;
    private String title;
    private String overview;
    private int code;

}
