package com.ib.cat.controller.media;

import com.ib.cat.dto.media.*;
import com.ib.cat.dto.member.LoginDto;
import com.ib.cat.entity.ContentReply;
import com.ib.cat.service.media.ContentsService;
import com.ib.cat.service.media.LikeService;
import com.ib.cat.service.media.MediaReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class MediaContentsController {

    @Autowired
    private ContentsService contentsService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private MediaReplyService mediaReplyService;

    @RequestMapping(value="/{type}/content/{contentsNum}", method= RequestMethod.GET)
    public ModelAndView detail(Model model,
                               @PathVariable(value="type") String contentsType,
                               @PathVariable("contentsNum") int contentsNum //@RequestParam(value="userId") String userId
                               ) { //HttpSession session,
        System.out.println("contentsController 작동중");

        model.addAttribute("contentsNum", contentsNum);
        //contentsNum(id) 컨텐츠 Dto 가져옴
        ContentsDto contents = (ContentsDto) contentsService.getSpecificContent(contentsType, contentsNum);

//        List<String> imageList = contentsService.getImages(contentsType, contentsNum);
        List<CreditsDto> cast = contentsService.getCredits(contentsType, contentsNum, "cast");
//        List<CreditsDto> crew = contentsService.getCredits(contentsType, contentsNum, "crew");

        /*  view 경로 분기  */
        int code=0;
        ModelAndView mav = new ModelAndView();
        if (contentsType.equals("tv")) {
            mav.setViewName("tv/content");
            code=1;
            System.out.println("tv/content");
        } else {
            mav.setViewName("movie/content");
            code=0;
            System.out.println("movie/content");
        }
        mav.addObject("code", code);
        mav.addObject("contents", contents); //        mav.addObject("imageList", imageList);
        mav.addObject("cast", cast); //        mav.addObject("crew", crew);
        mav.addObject("review",new ReviewDto()); // <- 얘는 form태그 테이터 전송을 위한 dto

        /*  리뷰 로딩  */
        List<ReviewDto> ctr = mediaReplyService.getReviews(contentsNum, code); // <- 반환 컬렉션 = 엔티티
        mav.addObject("reviews", ctr);

        /*  OTT 찾기  */
        List<OttDto> ol = contentsService.getOTT(contentsType, contentsNum);
        System.out.println("controller-list: "+ol);
        mav.addObject("ott", ol);

        return mav;
    }
}
