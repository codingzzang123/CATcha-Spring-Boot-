package com.ib.cat.controller.media;

import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.utils.sort.SortCri;
import com.ib.cat.service.media.ContentsService;
import com.ib.cat.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MediaListController {
    @Autowired
    private ContentsService contentsService;
    @Autowired
    private PagingUtil pagingUtil;

//    @RequestMapping(value="movie/list")
    @RequestMapping(value={"/tv/list", "/movie/list"})
    public ModelAndView main(
            HttpServletRequest request,
            Model model, SortCri cri,
            @RequestParam(value="type", defaultValue="movie")String contentsType,
            @RequestParam(value="page", defaultValue="1")Integer currentPage) {

        System.out.println("cat:" + cri.getCategory());

        String category;
        category = cri.getCategory(); //cri.getCategory -> String sortBy
        if (category==null) {		//기본값 인기도 내림차순
            category="popularity.desc";
//			System.out.println("Controller 첫단 category: "+category);
//			System.out.println("Controller currentPage: " + currentPage);
        }

        List<ContentsDto> mediaList = null;
        mediaList = contentsService.getInfoPageList(contentsType, category, currentPage);
//		System.out.println("Ctrl - contentsType: "+contentsType);
//		System.out.println("Ctrl - category: "+category);
        pagingUtil.startPaging(currentPage, 10000);

        //PagingUtil작업..!!
//        String paging = null;
//        /*pagingUtil 생성자에 들어갈 totalPage - json 파싱 결과물에서 가져오기*/
////		int totalPage = movieList.get(0).getTotalPages();
//        int totalPage = 500;//무료 API로 호출시 500page까지만 제공돼서 그냥 500으로 기재
//        if(currentPage > totalPage) {
//            currentPage = totalPage;
//        }
//		String listUrl = request.getContextPath()+"/movie/list";
//
//		paging = pagingUtil.paging(currentPage, totalPage, listUrl);
//		paging = paging.replaceAll("#####",  category); //url에 들어있는 특수문자${가 해석이 안 돼서 바꿈
//		System.out.println(paging);

        ModelAndView mav = new ModelAndView();
        if (request.getServletPath().equals("/tv/list")) {
            mav.setViewName("/tv/list");
            System.out.println("/tv/list");
        } else {
            mav.setViewName("/movie/list");
        }
        mav.addObject("mediaList", mediaList);
        mav.addObject("category", category);
        mav.addObject("scmd", cri);
        mav.addObject("page", currentPage);
        mav.addObject("paging", pagingUtil);

        return mav;
    }

}
