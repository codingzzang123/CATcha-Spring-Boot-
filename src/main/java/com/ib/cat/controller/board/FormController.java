package com.ib.cat.controller.board;

import com.ib.cat.entity.Board;
import com.ib.cat.repository.BoardRepository;
import com.ib.cat.service.board.BoardReplyService;
import com.ib.cat.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
public class FormController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    BoardReplyService boardReplyService;

    @GetMapping(value = "/board")
    public String listForm(Model model,
                           @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC)Pageable pageable,
                           @RequestParam(required = false, defaultValue = "",name = "s_type")String field,
                           @RequestParam(required = false, defaultValue = "",name = "s_keyword")String searchKeyword){
        Page<Board> pageList = boardRepository.findAll(pageable);

        if(field.equals("name")){
            pageList = boardService.search1(searchKeyword, pageable);
        }else if(field.equals("title")){
            pageList = boardService.search2(searchKeyword, pageable);
        }else{
            pageList = boardService.findAll(pageable);
        }
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
        model.addAttribute("comment", boardReplyService.listComment(0, no));
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
