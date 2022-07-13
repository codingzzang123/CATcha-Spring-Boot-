package com.ib.cat.controller.media;

import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.dto.media.CreditsDto;
import com.ib.cat.dto.media.LikeDto;
import com.ib.cat.dto.member.LoginDto;
import com.ib.cat.service.media.ContentsService;
import com.ib.cat.service.media.LikeService;
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
import java.util.List;

@Controller
public class MediaContentsController {

    @Autowired
    private ContentsService contentsService;
    @Autowired
    private LikeService likeService;

    @RequestMapping(value="/{type}/content/{contentsNum}", method= RequestMethod.GET)
    public ModelAndView detail(Model model, HttpSession session,
                               @PathVariable(value="type") String contentsType,
                               @PathVariable("contentsNum") int contentsNum) {
        System.out.println("Controller 작동중");
        System.out.println("type: " + contentsType);
        System.out.println("contentsNum: "+ contentsNum);
        String userId="";
        LoginDto loginDto = (LoginDto) session.getAttribute("auth");
        if (loginDto != null ) {
            userId = loginDto.getId();
        }
        System.out.println("userId : "+userId);

        model.addAttribute("contentsNum", contentsNum);
        //contentsNum(id) 컨텐츠 Dto 가져옴
        ContentsDto contents = (ContentsDto) contentsService.getSpecificContent(contentsType, contentsNum);

        List<String> imageList = contentsService.getImages(contentsType, contentsNum);

        List<CreditsDto> cast = contentsService.getCredits(contentsType, contentsNum, "cast");
        List<CreditsDto> crew = contentsService.getCredits(contentsType, contentsNum, "crew");

//      /*  추천  */
//		List<ContentsVO> reco = new ArrayList<ContentsVO>();
//
//		List<ContentsVO> temp = new ArrayList<ContentsVO>();
//		temp = contentsUtil.getInfoList(contentsType, sortBy); //전체가져오기
//		for(int i = 0 ; i < temp.size() ; i++) {
//			List<Integer> list1 = new ArrayList<>(temp.get(i).getGenres());//모든 컨텐츠의 장르
//			List<Integer> list2 = new ArrayList<>(contents.getGenres());//상세 페이지 컨텐츠 장르
//
//			//등록 장르 1개인 경우
//			if(list2.size() == 1) {
//				list1.retainAll(list2);
//				if (list1.size() == 1) {
//					ContentsVO vo = new ContentsVO();
//					vo = temp.get(i);
//					reco.add(vo);
//				}
//			} else if (list2.size() == 2) {
//				list1.retainAll(list2);
//				if (list1.size() == 2) {
//					ContentsVO vo = new ContentsVO();
//					vo = temp.get(i);
//					reco.add(vo);
//				}
//			} else {
//				list1.retainAll(list2);
//				if(list1.size() >= 3) {//겹치는 장르가 최소 3개 이상인 경우
//					ContentsVO vo = new ContentsVO();
//					vo = temp.get(i);
//					reco.add(vo);
//				}
//			}
//		}

        /*  view 경로 분기  */
        ModelAndView mav = new ModelAndView();
        if (contentsType.equals("tv")) {
            mav.setViewName("tv/content");
            System.out.println("tv/content");
        } else {
            mav.setViewName("movie/content");
            System.out.println("movie/content");
        }

        mav.addObject("contents", contents);
        mav.addObject("imageList", imageList);
        mav.addObject("cast", cast);
        mav.addObject("crew", crew);
//		mav.addObject("reco", reco); //추천 컨텐츠

        /*   좋아요 확인   */
        if (userId != null) { //로그인 상태 O
            LikeDto likeDto = new LikeDto();
            likeDto.setNum(contentsNum);
            likeDto.setName(userId); //유저 아이디 (${auth.id}

            int code;  //movie:0, tv:1, board:3
            if (contentsType.equals("movie")) {
                code = 0;
            } else if (contentsType.equals("tv")) {
                code = 1;
            } else code = 3; // code 3은 이 컨트롤러에서는 쓰이지 않음.
            likeDto.setCode(code);

            Boolean flag = false; // flag : true -> 있음, false -> 없음
            flag = likeService.checkLike(likeDto);
            mav.addObject("flag", flag);

            System.out.println("code: " + code);
            System.out.println("flag: " + flag);
            
        } else if (userId == null) { //로그인 x 상태
            mav.addObject("userId", "default");
            mav.addObject("flag", false);
        }


        return mav;
    }
}
