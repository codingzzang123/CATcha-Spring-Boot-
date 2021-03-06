package com.ib.cat.controller.main;

import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.service.main.MainService;
import com.ib.cat.service.media.ContentsService;
import com.ib.cat.utils.sort.SortByVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    ContentsService contentsService;

    @Autowired
    MainService mainService;

    private static final List<Session> session = new ArrayList<>();

    @GetMapping("/main")
    public ModelAndView mainForm(HttpServletRequest request, HttpServletResponse response) {

        List<ContentsDto> nowPlay = null;
        List<ContentsDto> popular = null;
        List<ContentsDto> highLate = null;

        nowPlay = contentsService.getMovieList(1);
        popular = contentsService.getMovieList(2);
        highLate = contentsService.getMovieList(3);

        Collections.sort(nowPlay, new SortByVote());
        Collections.sort(popular, new SortByVote());

        ModelAndView mav = new ModelAndView();
        mav.setViewName("main/main");
        mav.addObject("nowPlay", nowPlay);
        mav.addObject("popular", popular);
        mav.addObject("highLate", highLate);

        /* 쿠키+최근 게시물 관련 Object 추가 */
        mainService.isCookie(request,response);
        mav.addObject("today",mainService.getToday());
        mav.addObject("total",mainService.getTotal());
        mav.addObject("ls",mainService.getBoardTop());
        
        return mav;
    }


}
