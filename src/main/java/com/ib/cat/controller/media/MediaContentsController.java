package com.ib.cat.controller.media;

import com.ib.cat.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MediaContentsController {

    @Autowired
    private ContentsService contentsService;

    @RequestMapping(value="/movie/content/{contentsNum}", method=RequestMethod.GET)
    public ModelAndView detail(Model model,
                               @RequestParam(value="type", defaultValue="movie") String contentsType,
                               @RequestParam(value="sortBy", defaultValue="popularity.desc") String sortBy,
                               @PathVariable("contentsNum") int contentsNum) {
        System.out.println("Controller 작동중");
        System.out.println("type: " + contentsType);
        System.out.println("contentsNum: "+ contentsNum);

        model.addAttribute("contentsNum", contentsNum);
        //contentsNum(id) 컨텐츠VO 가져옴
        ContentsVO contents = (ContentsVO) contentsUtil.getSpecificContent(contentsType, contentsNum);

        List<String> imageList = contentsUtil.getImages(contentsType, contentsNum);

        List<CreditsVO> cast = contentsUtil.getCredits(contentsType, contentsNum, "cast");
        List<CreditsVO> crew = contentsUtil.getCredits(contentsType, contentsNum, "crew");

//		List<ContentsVO> reco = new ArrayList<ContentsVO>();

        //reco만들기
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
//
        ModelAndView mav = new ModelAndView();
        mav.setViewName("movie/content");
        mav.addObject("contents", contents);
        mav.addObject("imageList", imageList);
        mav.addObject("cast", cast);
        mav.addObject("crew", crew);
//		mav.addObject("reco", reco); //추천 컨텐츠

        return mav;
    }

    @RequestMapping("/content/search")
    public ModelAndView process(@RequestParam String keywordHeader,
                                @RequestParam(value="category", defaultValue="contents") String category,
                                @RequestParam String sortBy) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("contentsSearch");

        if (category.equals("contents") || category == null ) {
            ContentsUtil util = new ContentsUtil();

            /*영화에 해당하는 검색 결과와 시리즈에 해당하는 검색 결과를
             * 각각 추출하여 별도의 새로운 List 객체에 담기 위한 List 생성*/
            List<ContentsVO> searchResult = new ArrayList<ContentsVO>();

            List<ContentsVO> movie = null;
            movie = util.getInfoList("movie", sortBy);
            for (int i = 0 ; i < movie.size() ; i++) {
                //전체 영화 목록 중 제목,줄거리에 keyword가 포함된 vo객체만 따로 추출
                if(movie.get(i).getTitle().contains(keywordHeader) || movie.get(i).getOverview().contains(keywordHeader)) {
                    ContentsVO contents = new ContentsVO();
                    contents = movie.get(i); // 조건에 해당하는 경우만 vo에 저장...
                    searchResult.add(contents);
                }
            }

            List<ContentsVO> tv = null;
            tv = util.getInfoList("tv", sortBy);
            for (int i = 0 ; i < movie.size() ; i++) {
                //전체 TV 목록 중 제목,줄거리에 keyword가 포함된 vo객체만 따로 추출
                if(tv.get(i).getTitle().contains(keywordHeader) || movie.get(i).getOverview().contains(keywordHeader)) {
                    ContentsVO contents = new ContentsVO();
                    contents = tv.get(i); // 조건에 해당하는 경우만 vo에 저장...
                    searchResult.add(contents);
                }
            }

            Collections.sort(searchResult, new SortByDate());
            mav.addObject("searchResult", searchResult);
        }
//		} else if(category.equals("user")) { //검색 대상이 user면? 나중에 ㄱ현해
//			List<>
//		}
        return mav;

    }


}
