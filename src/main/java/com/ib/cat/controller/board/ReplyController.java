package com.ib.cat.controller.board;

import com.ib.cat.entity.BoardReply;
import com.ib.cat.service.board.BoardReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyController {
    @Autowired
    BoardReplyService boardReplyService;

    @PostMapping("/board/comment")
    public String comment(BoardReply boardReply, Model model){
        System.out.println("no : " + boardReply.getBoardNo());
        System.out.println("writer : " + boardReply.getWriter());
        System.out.println("content : " + boardReply.getContent());
        System.out.println("ref : " + boardReply.getRef());
        System.out.println("step : " + boardReply.getStep());
        System.out.println("depth : " + boardReply.getDepth());
        model.addAttribute("board", boardReply.getBoardNo());
        boardReplyService.insertComment(boardReply);
        return "redirect:/board/"+boardReply.getBoardNo();
    }
}
