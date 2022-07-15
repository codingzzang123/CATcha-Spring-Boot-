package com.ib.cat.dto.Info;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@AllArgsConstructor
@Data
public class ListMediaDto {
    private String content; //작성한 리뷰 내용
    private String id; //지금은 id로 저장 -> 나중에 name으로 바꿀수도있음
    private int code;
    private int contentNum;
    private Timestamp regdate;
    /* title 필드가 추가 되면 좋을것 같음 */
}
