package com.ib.cat.controller.main;

import com.ib.cat.dto.main.SearchCountDto;
import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.entity.Board;
import com.ib.cat.service.board.BoardService;
import com.ib.cat.service.main.SearchService;
import com.ib.cat.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {
    @Autowired
    SearchService searchService;

    @Autowired
    BoardService boardService;

    @Autowired
    PagingUtil pagingUtil; // 영화,TV 데이터들은 DB에 저장된것이 아니라 JSON 형태의 데이터이므로 Pageable 사용 하지않고 직접 페이징로직

    @GetMapping(value = {"/search", "/search/{path}"})
    public String searchFrom(Model model, @PathVariable Optional<String> path,
                             @RequestParam String query,
                             @RequestParam(value="page", defaultValue="1")int page, //페이징을 위한 변수
                             @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable, // Pageable객체를 사용한 페이징 처리 ( 게시판(board) 정보는 DB에 저장되어있으므로 )
                             HttpServletRequest request) throws UnsupportedEncodingException {

        if(query.isEmpty()||query.startsWith(" "))
            return "redirect:" + request.getHeader("Referer");

        String type = null;
        String resultPath= path.isPresent() ?  path.get() : "movie";
        SearchCountDto searchCountDto = searchService.searchCountDto(query);
        List<ContentsDto> contents = new ArrayList<>();

        if (resultPath.equals("movie")) {
            contents = searchService.movie(page, URLEncoder.encode(query, "UTF-8"));
            pagingUtil.startPaging(page, searchCountDto.getMovie());
            type = "movie";

        } else if (resultPath.equals("tv")) {
            contents = searchService.tv(page, URLEncoder.encode(query, "UTF-8"));
            pagingUtil.startPaging(page, searchCountDto.getTv());
            type = "tv";

        } else {
            type = "board";
            Page<Board> pageList = boardService.searchSubjectContent(query,pageable);

            /* 페이징을 위한 변수 */
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
        model.addAttribute("paging", pagingUtil);
        model.addAttribute("page", page);
        model.addAttribute("query", query);
        model.addAttribute("type", type);
        model.addAttribute("scd", searchCountDto);
        model.addAttribute("today", new Timestamp(System.currentTimeMillis()));

        return "main/search";
    }
}
