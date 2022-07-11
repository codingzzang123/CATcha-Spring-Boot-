package com.ib.cat.controller.main;

import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.dto.main.SearchCountDto;
import com.ib.cat.service.main.SearchService;
import com.ib.cat.utils.PagingUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ib.cat.utils.sort.SortByVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @Autowired
    SearchService searchService;
    @Autowired
    PagingUtil pagingUtil;

@RequestMapping(value = {"/search", "/search/{path}"})
    public String searchFrom(Model model, @PathVariable Optional<String> path,
                             @RequestParam(value="query", defaultValue=" ")String query,
                             @RequestParam(value="page", defaultValue="1")Integer page){

        String resultPath="";
        if(path.isPresent())
            resultPath=path.get();

        SearchCountDto scd = new SearchCountDto();
        scd = searchService.scd(query); // query가 포함된 movie,tv 수
        scd.setBoard(searchService.boardTuples(query)); // query가 포함된 게시물의 수

        List<ContentsDto> contents= new ArrayList<>();
        String type=null;

        if(resultPath.equals("")||resultPath.equals("movie")||resultPath==null){
            contents = searchService.movie(page,query); Collections.sort(contents, new SortByVote());
            pagingUtil.startPaging(page, scd.getMovie());
            type = "movie";

        }else if(resultPath.equals("tv")){
            contents = searchService.tv(page,query); Collections.sort(contents, new SortByVote());
            pagingUtil.startPaging(page, scd.getTv());
            type = "tv";

        }else{
            pagingUtil.startPaging(page, scd.getBoard());
            type="board";
            model.addAttribute("content",searchService.board(query)); //게시물 같은 경우 content로 반환
        }

        model.addAttribute("contents", contents);
        model.addAttribute("page", page);
        model.addAttribute("query", query);
        model.addAttribute("type", type);
        model.addAttribute("scd", scd);
        model.addAttribute("paging",pagingUtil);
        return "main/search";
    }
}
