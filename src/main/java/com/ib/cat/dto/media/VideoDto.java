package com.ib.cat.dto.media;

import lombok.Data;

@Data
public class VideoDto {
    private String name; //Clip 이름
    private String key; //url에 연결할 key값
//    private String site; //Youtube
    private boolean official; //true: official
}
