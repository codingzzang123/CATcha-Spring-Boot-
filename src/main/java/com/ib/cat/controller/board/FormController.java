package com.ib.cat.controller.board;

import com.ib.cat.service.board.BoardReplyService;
import com.ib.cat.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FormController {

    @Autowired
    private BoardService boardService;
    @Autowired
    BoardReplyService boardReplyService;

    @GetMapping(value = "/board")
    public String listForm(Model model){
        model.addAttribute("boards",boardService.getAll());
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
