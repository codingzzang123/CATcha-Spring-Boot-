package com.ib.cat.controller.media;

import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.utils.sort.SortCri;
import com.ib.cat.service.media.ContentsService;
import com.ib.cat.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value="/{type}/list")
    public ModelAndView main(Model model,
            @PathVariable(value="type") String contentsType,
            @RequestParam(value="page", defaultValue="1") Integer currentPage,
            @RequestParam(value="category", defaultValue="popularity.desc") String category,
                             @RequestParam(value="platform", defaultValue="") String platform
    ) {

//        category = cri.getCategory(); //cri.getCategory -> String sortBy
        if (category==null) {		//기본값 인기도 내림차순
            category="popularity.desc";
        }
//        platform = cri.getPlatform();
        if (platform==null) {
            platform="";
        }

        List<ContentsDto> mediaList = null;

        mediaList = contentsService.getInfoPageList(contentsType, category, currentPage, platform);
        pagingUtil.startPaging(currentPage, 10000);

        ModelAndView mav = new ModelAndView();
        if (contentsType.equals("tv")) {
            System.out.println("/tv/list 실행");
            mav.setViewName("/tv/list");
            if (category.equals("popularity.desc")) {
                mav.addObject("category", "popularity.desc");
            } else {
                mav.addObject("category", "vote_average.desc");
            }
        } else {
            System.out.println("/movie/list 실행");
            mav.setViewName("/movie/list");
            if (category.equals("popularity.desc")) {
                mav.addObject("category", "popularity.desc");
            } else {
                mav.addObject("category", "vote_average.desc");
            }
        }
        System.out.println(platform);
        mav.addObject("mediaList", mediaList);
//        mav.addObject("scmd", cri);
        mav.addObject("category", category);
        mav.addObject("platform", platform);
        mav.addObject("page", currentPage);
        mav.addObject("paging", pagingUtil);

        return mav;
    }

}
