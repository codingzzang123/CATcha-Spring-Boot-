package com.ib.cat.controller.board;

import com.ib.cat.entity.BoardReply;
import com.ib.cat.service.board.BoardReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
    @Autowired
    BoardReplyService boardReplyService;

    @PostMapping("/board/comment")
    public String comment(BoardReply boardReply, Model model){

        model.addAttribute("board", boardReply.getBoardNo());
        boardReplyService.insertComment(boardReply);
        return "redirect:/board/"+boardReply.getBoardNo();
    }

    @PostMapping("/board/reply")
    public String reply(BoardReply boardReply, Model model){
        boardReply.setRef(1);

        model.addAttribute("board", boardReply.getBoardNo());
        boardReplyService.insertComment(boardReply);
        return "redirect:/board/"+boardReply.getBoardNo();
    }

    @RequestMapping(value="/board/deleteComment", method = {RequestMethod.POST})
    @ResponseBody
    public void oldPwCheck(@RequestParam("no") int no) {
        if(boardReplyService.findByNo(no).getRef() == 0 && boardReplyService.commentCheck(no).size() != 0){
            BoardReply boardReply = boardReplyService.findByNo(no);
            boardReply.setContent("삭제된 댓글입니다.");
            boardReplyService.updateComment(boardReply);
        } else if(boardReplyService.findByNo(no).getRef() == 1 
                && boardReplyService.commentCheck(boardReplyService.findByNo(no).getStep()).size() == 1
                && boardReplyService.findByNo(boardReplyService.findByNo(no).getStep()).getContent().equals("삭제된 댓글입니다.")) {
            boardReplyService.deleteComment(boardReplyService.findByNo(no).getStep());
            boardReplyService.deleteComment(no);
        } else{
            boardReplyService.deleteComment(no);
        }
    }
}
