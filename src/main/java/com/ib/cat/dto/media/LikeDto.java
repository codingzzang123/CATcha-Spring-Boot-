package com.ib.cat.dto.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {
    private String name; //회원 닉네임 (${auth.name})
    private int num; //contentsNum
    private String poster; //member image
    private String title;
    private String overview; //contents
    private int code; //movie:0, tv:1, board:3

}
