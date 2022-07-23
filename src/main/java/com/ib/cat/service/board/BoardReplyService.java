package com.ib.cat.service.board;

import com.ib.cat.entity.BoardReply;
import com.ib.cat.repository.BoardReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardReplyService {
    @Autowired
    BoardReplyRepository boardReplyRepository;

    public void insertComment(BoardReply boardReply) {
        boardReplyRepository.save(boardReply);
    }
    public void updateComment(BoardReply boardReply) {
        boardReplyRepository.save(boardReply);
    }
    public List<BoardReply> listComment(int ref, int boardNo){
        return boardReplyRepository.findByRefAndBoardNoOrderByNoAsc(ref, boardNo);
    }
    public List<BoardReply> listReply(int ref, int boardNo){
        return boardReplyRepository.findByRefAndBoardNoOrderByRegdateAsc(ref, boardNo);
    }
    public void deleteComment(int no){
        boardReplyRepository.delete(boardReplyRepository.findById(no).get());
    }

    public BoardReply findByNo(int no){return boardReplyRepository.findById(no).get();}
    public List<BoardReply> commentCheck(int step){
        return boardReplyRepository.findByStep(step);
    }
}
