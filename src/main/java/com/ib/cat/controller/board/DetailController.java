package com.ib.cat.controller.board;

import com.ib.cat.entity.Board;
import com.ib.cat.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller

public class DetailController {
    @Autowired
    private BoardService boardService;


    @GetMapping(value="/board/{no}")
    public String detail(@PathVariable("no") int no, Model model) {
        System.out.println("detail실행");
        System.out.println(no);
        Board board = boardService.findById(no);

        model.addAttribute("board", board);
        return "/board/detail";
    }
}
