package com.ib.cat.service.board;

import com.ib.cat.dto.Board.BoardCommentDto;
import com.ib.cat.entity.BoardReply;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    BoardReplyService boardReplyService;
    @Autowired
    MemberService memberService;

    public List<BoardCommentDto> commentList(int no){
        List<BoardCommentDto> commentResult = new ArrayList<>();
        List<BoardReply> commentList = boardReplyService.listComment(0, no);
        for(int i=0; i< commentList.size(); i++){
            BoardCommentDto comment = new BoardCommentDto();
            comment.setNo(commentList.get(i).getNo());
            comment.setWriter(commentList.get(i).getWriter());
            comment.setBoardNo(commentList.get(i).getBoardNo());
            comment.setContent(commentList.get(i).getContent());
            comment.setRegdate(commentList.get(i).getRegdate());
            comment.setRef(commentList.get(i).getRef());
            comment.setStep(commentList.get(i).getStep());
            comment.setImgs(memberService.findByName(commentList.get(i).getWriter()).getImgs());
            commentResult.add(i, comment);
        }
        return commentResult;
    }

    public List<BoardCommentDto> replyList(int no){
        List<BoardCommentDto> replyResult = new ArrayList<>();
        List<BoardReply> commentList = boardReplyService.listComment(1, no);
        for(int i=0; i< commentList.size(); i++){
            BoardCommentDto comment = new BoardCommentDto();
            comment.setNo(commentList.get(i).getNo());
            comment.setWriter(commentList.get(i).getWriter());
            comment.setBoardNo(commentList.get(i).getBoardNo());
            comment.setContent(commentList.get(i).getContent());
            comment.setRegdate(commentList.get(i).getRegdate());
            comment.setRef(commentList.get(i).getRef());
            comment.setStep(commentList.get(i).getStep());
            comment.setImgs(memberService.findByName(commentList.get(i).getWriter()).getImgs());
            replyResult.add(i, comment);
        }
        return replyResult;
    }
}
