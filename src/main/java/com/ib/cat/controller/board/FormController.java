package com.ib.cat.controller.board;

import com.ib.cat.dto.Board.BoardCommentDto;
import com.ib.cat.entity.Board;
import com.ib.cat.service.board.BoardReplyService;
import com.ib.cat.service.board.BoardService;
import com.ib.cat.service.board.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class FormController {

    @Autowired
    private BoardService boardService;
    @Autowired
    CommentService commentService;
    @Autowired
    BoardReplyService boardReplyService;

    @GetMapping(value = "/board")
    public String listForm(Model model,
                           @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC)Pageable pageable,
                           @RequestParam(required = false, defaultValue = "",name = "s_type")String field,
                           @RequestParam(required = false, defaultValue = "",name = "s_keyword")String searchKeyword,
                           @RequestParam(value="category", defaultValue="none") String cate){
        Page<Board> pageList;
        System.out.println(cate);

        if (cate.equals("none")) { /* 전체보기 */
            System.out.println("전체보기");
            if(field.equals("subject_memo")){
                pageList = boardService.searchSubjectMemo(searchKeyword,searchKeyword,pageable);
            }else if(field.equals("subject")){
                pageList = boardService.searchSubject(searchKeyword, pageable);
            }else if(field.equals("memo")){
                pageList = boardService.searchMemo(searchKeyword,pageable);
            }else if(field.equals("name")){
                pageList = boardService.searchName(searchKeyword, pageable);
            }else{
                pageList = boardService.findAll(pageable);
                System.out.println("여기?");
                System.out.println("field: "+field);
                System.out.println("searchKeyword: "+searchKeyword);
            }
        } else { /* 말머리 선택 */
            System.out.println("선택");
            if(field.equals("subject_memo")) {
                pageList = boardService.searchCateAndSubjectMemo(cate,searchKeyword,searchKeyword,pageable);
                System.out.println("searchCateAndSubjectMemo:"+searchKeyword);
            } else if (field.equals("subject")) {
                pageList = boardService.searchCateAndSubject(cate, searchKeyword, pageable);
                System.out.println("searchCateAndSubject:"+searchKeyword);
            } else if (field.equals("memo")) {
                pageList = boardService.searchCateAndMemo(cate, searchKeyword, pageable);
                System.out.println("searchCateAndMemo:"+searchKeyword);
            } else if (field.equals("name")) {
                pageList = boardService.searchCateAndName(cate, searchKeyword, pageable);
                System.out.println("searchCateAndName:"+searchKeyword);
            } else {
                pageList = boardService.findAllCate(cate, pageable);
                System.out.println("field: "+field);
                System.out.println("searchKeyword: "+searchKeyword);
            }
        }


//        if(field.equals("subject_memo")){
//            pageList = boardService.searchSubjectMemo(searchKeyword,searchKeyword,pageable);
//        }else if(field.equals("subject")){
//            pageList = boardService.searchSubject(searchKeyword, pageable);
//        }else if(field.equals("memo")){
//            pageList = boardService.searchMemo(searchKeyword,pageable);
//        }else if(field.equals("name")){
//            pageList = boardService.searchName(searchKeyword, pageable);
//        }else{
//            pageList = boardService.findAll(pageable);
//        }

        int pageNumber = pageList.getPageable().getPageNumber();
        int totalPages = pageList.getTotalPages();
        int pageBlock = 10;
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("boards", pageList);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        model.addAttribute("today", timestamp);

        return "board/list";
    }

    @GetMapping(value = "/board/{no}")
    public String detailForm(@PathVariable Integer no, Model model){
        boardService.views(no);
        List<BoardCommentDto> commentResult = commentService.commentList(no);
        List<BoardCommentDto> replyResult = commentService.replyList(no);
        model.addAttribute("comment", commentResult);
        model.addAttribute("reply", replyResult);
        model.addAttribute("board", boardService.getBoard(no));
        return "board/detail";
    }

    @GetMapping(value = "/board/write")
    public String writeForm(){
        return "board/write";
    }

    @GetMapping(value = "/board/edit/{no}")
    public String editForm(@PathVariable Integer no, Model model){
        model.addAttribute("board",boardService.findBoard(no));
        return "/board/edit";
    }
}
