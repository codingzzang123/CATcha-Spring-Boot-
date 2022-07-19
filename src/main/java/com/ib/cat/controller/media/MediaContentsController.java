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
import java.util.ArrayList;
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
                               @PathVariable("contentsNum") int contentsNum
                               ) { //HttpSession session,

        model.addAttribute("contentsNum", contentsNum);
        //contentsNum(id) 컨텐츠 Dto 가져옴
        ContentsDto contents = (ContentsDto) contentsService.getSpecificContent(contentsType, contentsNum);

//        List<String> imageList = contentsService.getImages(contentsType, contentsNum);
        List<CreditsDto> cast = contentsService.getCredits(contentsType, contentsNum, "cast");
//        List<CreditsDto> crew = contentsService.getCredits(contentsType, contentsNum, "crew");
        List<ContentsDto> reco = new ArrayList<ContentsDto>();

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
        mav.addObject("ott", ol);

        /*  추천 장르  */

        List<ContentsDto> temp = new ArrayList<ContentsDto>();
        temp = contentsService.getInfoList(contentsType); //임시 리스트에 모든 컨텐츠 저장

        for (int i = 0 ; i < temp.size() ; i++) {
            List<Integer> list1 = new ArrayList<>(temp.get(i).getGenres()); //전체
//            System.out.println("controller - list1.size: "+ list1.size());
            List<Integer> list2 = new ArrayList<>(contents.getGenres()); //target
//            System.out.println("controller - list2: " + list2);

            if(list2.size() == 1) { //target-등록 장르 1개일때
                list1.retainAll(list2); //list1, list2 공통 요소만 list1에 남김
                if (list1.size() == 1) {
                    ContentsDto dto = new ContentsDto();
                    dto = temp.get(i);
                    reco.add(dto);
                }
            } else if (list2.size() == 2) {
                list1.retainAll(list2);
                if(list1.size() == 2) {
                    ContentsDto dto = new ContentsDto();
                    dto = temp.get(i);
                    reco.add(dto);
                }
            } else {
                list1.retainAll(list2);
                if(list1.size() >= 3) {
                    ContentsDto dto = new ContentsDto();
                    dto = temp.get(i);
                    reco.add(dto);
                }
            }
        }
        mav.addObject("reco", reco);

        return mav;
    }
}
