package com.ib.cat.controller.board;

import com.ib.cat.entity.Board;
import com.ib.cat.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UpdateController {
    @Autowired
    private BoardService boardService;

    //글 수정 페이지로 이동하는 기능
    @RequestMapping(value="/board/edit/{no}", method= RequestMethod.GET)
    public String update(@PathVariable("no") int no, Model model)throws Exception{
        System.out.println("no = " + no);
        boardService.findById(no);
        model.addAttribute("board", boardService.findById(no));
        return "/board/edit";
    }

    //글 수정 POST

    @PostMapping (value="/board/edit/{no}")
    public String update(Board board) throws Exception{
        boardService.update(board);
        return "redirect:/board";
    }
}
