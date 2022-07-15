package com.ib.cat.controller.media;

import com.ib.cat.dto.media.ContentReplyDto;
import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.dto.media.CreditsDto;
import com.ib.cat.dto.media.LikeDto;
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

        List<String> imageList = contentsService.getImages(contentsType, contentsNum);

        List<CreditsDto> cast = contentsService.getCredits(contentsType, contentsNum, "cast");
        List<CreditsDto> crew = contentsService.getCredits(contentsType, contentsNum, "crew");

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

        mav.addObject("contents", contents);
        mav.addObject("imageList", imageList);
        mav.addObject("cast", cast);
        mav.addObject("crew", crew);

        /*  리뷰 로딩  */
        List<ContentReply> ctr = mediaReplyService.getMediaReplyPage(code, contentsNum);
        System.out.println(ctr.toString());
        mav.addObject("contentReply", ctr);

        /*   좋아요 확인   */
//        if (userId != null) { //로그인 상태 O
//            LikeDto likeDto = new LikeDto();
//            likeDto.setNum(contentsNum);
//            likeDto.setName(userId); //유저 아이디 (${auth.id}
//
//            int code;  //movie:0, tv:1, board:3
//            if (contentsType.equals("movie")) {
//                code = 0;
//            } else if (contentsType.equals("tv")) {
//                code = 1;
//            } else code = 3; // code 3은 이 컨트롤러에서는 쓰이지 않음.
//            likeDto.setCode(code);
//
//            String flag = "false"; // flag : true -> 있음, false -> 없음
//            flag = String.valueOf(likeService.checkLike(likeDto));
//            mav.addObject("flag", flag);
//
//            System.out.println("code: " + code);
//            System.out.println("flag: " + flag);
//
//        } else if (userId == null) { //로그인 x 상태
//            mav.addObject("userId", "default");
//            mav.addObject("flag", "false");
//        }


        return mav;
    }
}
