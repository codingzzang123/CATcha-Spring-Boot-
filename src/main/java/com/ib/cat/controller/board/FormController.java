package com.ib.cat.controller.board;

import com.ib.cat.dto.Board.ListDto;
import com.ib.cat.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FormController {

    @Autowired
    private BoardService boardService; //controller에서 reposiroty연결 하려고? 웅 오빠 순서가 잘못된건가>?>

//
//    @GetMapping(value = "/board")
//    public String listForm(Model model,
//                           @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC)
//                           Pageable pageable){
//        List<Board> list = boardService.getAll();
//        //이렇게하면 오류가 안나긴하는데
//        // 구조가 이렇게되면 안될것같다 일단 해보고 컨트롤러->서비스->리파지토리 식으로 바꾸던가,
//        // 아니면 지금부터 구조를 저렇게 바꾸는게 좋을것같아 예시로 생성하는거 잘봐
//        // 요청은 컨트롤러에 먼저들오잖아 데이터 db에 넣으라고 그치?
//
//        //int pageNumber=list.getgetPageable().getPageNumber(); //현재페이지
//        //int totalPages=list.getTotalPages(); //총 페이지 수. 검색에따라 10개면 10개..
//        int pageBlock = 10; //블럭의 수
//        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //현재 페이지가 7이라면 1*5+1=6
//        int endBlockPage = startBlockPage+pageBlock-1; //6+5-1=10. 6,7,8,9,10해서 10.
//        endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;
//
//        model.addAttribute("startBlockPage", startBlockPage);
//        model.addAttribute("endBlockPage", endBlockPage);
//        model.addAttribute("list", list);
//        model.addAttribute("boards",boardService.getAll());
//        return "board/list";
//    }

    @GetMapping(value = "/board")
    public String listForm(Model model,
                          // @PathVariable(value="page", defaultValue="1")Integer currentPage,
                           @PageableDefault(size = 20, sort = "no", direction = Sort.Direction.DESC)
                           Pageable pageable) {

        List<ListDto> List = null;

//        pagingUtils.startPaging(currentPage, 10000);

  //      list.addAll("page", currentPage);
    //    list.addAll("paging", pagingUtil);
        //이렇게하면 오류가 안나긴하는데
        // 구조가 이렇게되면 안될것같다 일단 해보고 컨트롤러->서비스->리파지토리 식으로 바꾸던가,
        // 아니면 지금부터 구조를 저렇게 바꾸는게 좋을것같아 예시로 생성하는거 잘봐
        // 요청은 컨트롤러에 먼저들오잖아 데이터 db에 넣으라고 그치?

        //int pageNumber=list.getgetPageable().getPageNumber(); //현재페이지
        //int totalPages=list.getTotalPages(); //총 페이지 수. 검색에따라 10개면 10개..


        return "board/list";

    }

    @GetMapping(value = "/board/{no}")
    public String detailForm(@PathVariable Integer no, Model model){
        model.addAttribute("board", boardService.getOne(no));
        return "board/detail";
    }

    @GetMapping(value = "/board/write")
    public String writeForm(){
        return "board/write";
    }


    @GetMapping(value = "/board/edit/{no}")
    public String editForm(@PathVariable Integer no, Model model){
        model.addAttribute("board",boardService.getOne(no));
        return "/board/edit";
    }

}
