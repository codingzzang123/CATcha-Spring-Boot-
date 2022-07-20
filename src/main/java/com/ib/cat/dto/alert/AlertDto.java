package com.ib.cat.dto.alert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
public class AlertDto {
    private int no;
    private String subName;
    private String pubName;
    private int bno;
    private int code;
    private Timestamp regdate;
    private String imgs;

    private String title;

    @Builder
    public AlertDto(String subName,String pubName,int bno,int code){
        this.subName =subName;
        this.pubName = pubName;
        this.bno = bno;
        this.code = code;
    }

    @Builder
    public AlertDto(int no, String subName,String pubName,int bno,int code,Timestamp regdate,String imgs,String title){
        this.no = no;
        this.subName =subName;
        this.pubName = pubName;
        this.bno = bno;
        this.code = code;
        this.regdate = regdate;
        this.imgs = imgs;
        this.title = title;
    }

}
