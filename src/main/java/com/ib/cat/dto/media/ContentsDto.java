package com.ib.cat.dto.media;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class ContentsDto {
    private int contentsNum;//v
    private String contentsType;//v
    private String title;//v
    private String overview;//v

    @Column(columnDefinition = "default 'cinema_default.png'")
    private String posterPath;//v
    private Date releaseDate;//v
    private float voteAverage;//v
    private float popularity;//v
    private String genre; //컨텐츠 장르
    private List<Integer> genres;//v

    private String runtime;
    private int hour;
    private int minute;


    private int count;
    private Double star; //평점
    //	private GenresVO genresVO;
    /**/
    private List<GenresDto> ls;

    private int totalResults;
    private int totalPages;
    private int page;

}
