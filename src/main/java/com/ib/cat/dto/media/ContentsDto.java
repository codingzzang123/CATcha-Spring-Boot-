package com.ib.cat.dto.media;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class ContentsDto {
    private int contentsNum;//컨텐츠 id
    private String contentsType;//movie, tv
    private String title;//
    private String overview;//

    @Column(columnDefinition = "default 'cinema_default.png'")
    private String posterPath;//포스터 사진
    private Date releaseDate;//공개일
    private float voteAverage;//평점
    private float popularity;//인기
    private String genre; //컨텐츠 장르
    private List<Integer> genres;//컨텐츠 장르

    private String runtime;//런타임
    private int hour;
    private int minute;

    private int count;
    private Double star; //평점
    //	private GenresVO genresVO;
    /**/
    private List<GenresDto> ls;
//
//    private int totalResults;
//    private int totalPages;
//    private int page;

}
