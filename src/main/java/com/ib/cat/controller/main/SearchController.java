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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    PagingUtil pagingUtil;



@RequestMapping(value = {"/search", "/search/{path}"}, method= RequestMethod.GET)
    public String searchFrom(Model model, @PathVariable Optional<String> path,
                             @RequestParam(required = false, value="query")String query,
                             @RequestParam(value="page", defaultValue="1")Integer page,
                             @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
                             HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("query : "+ URLDecoder.decode(query, "UTF-8"));
        if(query.isEmpty()||query.startsWith(" ")||query==null||query.length()==0) {
            query = "!@#$%^&*";
            return "redirect:" + request.getHeader("Referer");
        }

        String resultPath="";
        String type = null;
        if (path.isPresent())
            resultPath = path.get();
        else
            resultPath = "movie";

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
        model.addAttribute("scd", searchCountDto);
        model.addAttribute("paging", pagingUtil);

        model.addAttribute("today", new Timestamp(System.currentTimeMillis()));
        return "main/search";

    }

}
