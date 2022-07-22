package com.ib.cat.controller.main;

import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.dto.main.SearchCountDto;
import com.ib.cat.entity.Board;
import com.ib.cat.service.board.BoardService;
import com.ib.cat.service.main.SearchService;
import com.ib.cat.utils.PagingUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ib.cat.utils.sort.SortByVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {
    @Autowired
    SearchService searchService;

    @Autowired
    BoardService boardService;

    @Autowired
    PagingUtil pagingUtil;

@RequestMapping(value = {"/search", "/search/{path}"})
    public String searchFrom(Model model, @PathVariable Optional<String> path,
                             @RequestParam(required = false, value="query",defaultValue = "")String query,
                             @RequestParam(value="page", defaultValue="1")Integer page,
                             @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable){

        if(query.isEmpty()||query.startsWith(" ")||query==null||query.length()==0)
            query="!@#$%^&*";
        String resultPath="";
        if (path.isPresent())
            resultPath = path.get();

        SearchCountDto scd = new SearchCountDto();
        scd = searchService.scd(query); // query가 포함된 movie,tv 수 -> query가 빈 문자열이면 null
        scd.setBoard(searchService.boardTuples(query)); // query가 포함된 게시물의 수

        List<ContentsDto> contents = new ArrayList<>();
        String type = null;

        if (resultPath.equals("") || resultPath.equals("movie") || resultPath == null) {
            contents = searchService.movie(page, query);
            pagingUtil.startPaging(page, scd.getMovie());
            type = "movie";

        } else if (resultPath.equals("tv")) {
            contents = searchService.tv(page, query);
            pagingUtil.startPaging(page, scd.getTv());
            type = "tv";

        } else {
            type = "board";
            /* 게시판 페이징 Attribute 추가 */
            Page<Board> pageList = boardService.searchSubjectMemo(query,query,pageable);

            int pageNumber = pageList.getPageable().getPageNumber();
            int totalPages = pageList.getTotalPages();
            int pageBlock = 5;
            int startBlockPage = ((pageNumber)/pageBlock)*pageBlock + 1;
            int endBlockPage = startBlockPage + pageBlock - 1;
            endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

            model.addAttribute("pageNumber", pageNumber);
            model.addAttribute("startBlockPage", startBlockPage);
            model.addAttribute("endBlockPage", endBlockPage);
            model.addAttribute("content", pageList);

        }

        model.addAttribute("contents", contents);
        model.addAttribute("page", page);
        model.addAttribute("query", query);
        model.addAttribute("type", type);
        model.addAttribute("scd", scd);
        model.addAttribute("paging", pagingUtil);
        return "main/search";

    }
}
