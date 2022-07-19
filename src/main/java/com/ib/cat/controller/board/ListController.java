package com.ib.cat.controller.board;

import com.ib.cat.entity.Board;
import com.ib.cat.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ListController {

    @Autowired
    private BoardService boardService;

    //글 목록
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public String list(Model model) {
        List<Board> list = boardService.findAll();
        model.addAttribute("list" ,list);
        return "/board/list";
    }
}

