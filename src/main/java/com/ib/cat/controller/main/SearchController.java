package com.ib.cat.controller.main;

import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.dto.main.SearchCountDto;
import com.ib.cat.service.main.SearchService;
import com.ib.cat.utils.PagingUtil;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ib.cat.utils.sort.SortMovieByVote;
import com.ib.cat.utils.sort.SortTvByVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {
    @Autowired
    SearchService searchService;

    @Autowired
    PagingUtil pagingUtil;

    private SearchCountDto scd;

    @RequestMapping(value={"/search", "/search/movie"}) //추가
    public String searchform1(Model model, HttpServletRequest request) {
        String query = request.getParameter("query");
        String pageNum = request.getParameter("page");
        int page;

        if(pageNum==null)
            page=1;
        else
            page = Integer.parseInt(pageNum);

        scd = searchService.scd(query);
        int blocks = scd.getMovie();

        pagingUtil.startPaging(page, blocks);

        List<ContentsDto> movieList = searchService.movie(page,query);
        Collections.sort(movieList, new SortMovieByVote());

        model.addAttribute("contents", movieList);
        model.addAttribute("page", page);
        model.addAttribute("query", query);
        model.addAttribute("scd", scd);

        /* 페이징 attribute 추가 */
        model.addAttribute("blockStartNum",pagingUtil.getBlockStartNum()); //블럭 시작 넘버 ,
        model.addAttribute("blockLastNum",pagingUtil.getBlockLastNum()); //추가한것1.
        model.addAttribute("now",pagingUtil.getCurPage()); //현재 페이지 위치
        model.addAttribute("end",pagingUtil.getLastPageNum()); //블럭 마지막 번호
        model.addAttribute("type","m");

        return "main/search";
    }

    @RequestMapping(value="/search/tv")
    public String searchform2(Model model, HttpServletRequest request) {
        String query = request.getParameter("query");
        String pageNum = request.getParameter("page");
        int page;

        if(pageNum==null)
            page=1;
        else
            page = Integer.parseInt(pageNum);

        scd = searchService.scd(query);
        int blocks = scd.getTv();
        pagingUtil.startPaging(page, blocks);

        List<ContentsDto> tvList = searchService.tv(page,query);
        Collections.sort(tvList, new SortTvByVote());

        model.addAttribute("contents", tvList);
        model.addAttribute("page", page);
        model.addAttribute("query", query);
        model.addAttribute("scd", scd);

        /* 페이징 attribute 추가 */
        model.addAttribute("blockStartNum",pagingUtil.getBlockStartNum()); //블럭 시작 넘버 ,
        model.addAttribute("blockLastNum",pagingUtil.getBlockLastNum()); //추가한것1.
        model.addAttribute("now",pagingUtil.getCurPage()); //현재 페이지 위치
        model.addAttribute("end",pagingUtil.getLastPageNum()); //블럭 마지막 번호
        model.addAttribute("type","t");
        return "main/search";
    }

//    @RequestMapping(value="/search/board")
//    public String searchform3(Model model, HttpServletRequest request) {
//        String query = request.getParameter("query");
//        String pageNum = request.getParameter("page");
//        int page;
//
//        if(pageNum==null)
//            page=1;
//        else
//            page = Integer.parseInt(pageNum);
//
//        scd = search.scd(query);
//        int blocks = scd.getBoard();
//        paging.startPaging(page, blocks);
//
//        List<MainDTO> boardList = search.board(page, query);
//        model.addAttribute("content", boardList);
//        model.addAttribute("page", page);
//        model.addAttribute("query", query);
//        model.addAttribute("scd", scd);
//
//        /* 페이징 attribute 추가 */
//        model.addAttribute("blockStartNum",paging.getBlockStartNum()); //블럭 시작 넘버 ,
//        model.addAttribute("blockLastNum",paging.getBlockLastNum()); //추가한것1.
//        model.addAttribute("now",paging.getCurPage()); //현재 페이지 위치
//        model.addAttribute("end",paging.getLastPageNum()); //블럭 마지막 번호
//        model.addAttribute("type","b");
//        return "main/search";
//    }

}
