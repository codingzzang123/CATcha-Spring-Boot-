package com.ib.cat.controller.board;

import com.ib.cat.dto.Board.ListDto;
import com.ib.cat.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class FormController {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/board")
    public String listForm(Model model, @PathVariable Optional<String>path,
                           @RequestParam(value="query", defaultValue = " ")String query,
                           @RequestParam(value="page", defaultValue = "1")Integer page){

        model.addAttribute("boards",boardService.getAll());



        List<ListDto> List = null;

        return "board/list";

    }

    @GetMapping(value = "/board/{no}")
    public String detailForm(@PathVariable Integer no, Model model){
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
