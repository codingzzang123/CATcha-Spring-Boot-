package com.ib.cat.service.main;

import com.ib.cat.dto.main.SearchBoardDto;
import com.ib.cat.dto.main.SearchCountDto;
import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.entity.Board;
import com.ib.cat.repository.BoardRepository;
import com.ib.cat.utils.SearchInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    SearchInfoUtil searchInfoUtil;

    @Autowired
    BoardRepository boardRepository;

    public List<ContentsDto> movie(int page, String query){

        List<ContentsDto> list = searchInfoUtil.getMovieList(page, query);

        return list;
    }

    public List<ContentsDto> tv(int page, String query){

        List<ContentsDto> list = searchInfoUtil.getTvList(page, query);

        return list;
    }


    public List<ContentsDto> movie(Integer page, String query){
        return searchInfoUtil.getMovieList(page, query);
    }

    public List<SearchBoardDto> board(String query){
        List<SearchBoardDto> list = new ArrayList<>();

        List<Board> tmp = boardRepository.findByTitleContainingOrContentContainingIgnoreCase(query,query);

        for(Board board : tmp){
            list.add(new SearchBoardDto(
                board.getNo(),board.getTitle(),board.getContent(),board.getName(),board.getRegdate()
            ));
        }
        return list;
    }

    public Integer boardTuples(String query){
        return boardRepository.countByTitleContainingOrContentContainingIgnoreCase(query,query);
    }

    public int movieTuples(String query){
        return searchInfoUtil.countMovies(query);
    }

    public int tvTuples(String query){
        return searchInfoUtil.countTVs(query);
    }

    public SearchCountDto searchCountDto(String query) throws UnsupportedEncodingException {
        SearchCountDto searchCountDto = new SearchCountDto();
        searchCountDto.setMovie(movieTuples(URLEncoder.encode(query, "UTF-8")));
        searchCountDto.setTv(tvTuples(URLEncoder.encode(query, "UTF-8")));
        searchCountDto.setBoard(boardTuples(query));
        return searchCountDto;
    }
}
