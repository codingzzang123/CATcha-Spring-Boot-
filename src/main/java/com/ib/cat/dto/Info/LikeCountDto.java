package com.ib.cat.dto.Info;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LikeCountDto {
    private int movie;
    private int tv;
    private int board;
}
