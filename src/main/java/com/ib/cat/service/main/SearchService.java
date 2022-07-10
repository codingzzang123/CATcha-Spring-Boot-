package com.ib.cat.service.main;

import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.dto.main.SearchCountDto;
import com.ib.cat.utils.SearchInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    SearchInfoUtil searchInfoUtil;

    public List<ContentsDto> movie(int page, String query){

        List<ContentsDto> list = searchInfoUtil.getMovieList(page, query);

        return list;
    }

    public List<ContentsDto> tv(int page, String query){

        List<ContentsDto> list = searchInfoUtil.getTvList(page, query);

        return list;
    }

    public SearchCountDto scd(String query) {
        return searchInfoUtil.contents(query);
    }

//    public List<MainDTO> board(int page,String query){
//        return contentsService.getBoardList(page, query);
//    }
}
