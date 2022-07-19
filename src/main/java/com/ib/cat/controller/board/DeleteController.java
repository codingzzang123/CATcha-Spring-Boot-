package com.ib.cat.controller.board;

import com.ib.cat.entity.Board;
import com.ib.cat.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteController {
    @Autowired
    private BoardService boardService;

    @GetMapping ("/board/delete/{no}")
    public String delete(@PathVariable("no") int no)throws Exception {
        Board board = boardService.findById(no);
        boardService.delete(board);
        return "redirect:/board";
    }

}
