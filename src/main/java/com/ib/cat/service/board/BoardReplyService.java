package com.ib.cat.service.board;

import com.ib.cat.entity.Board;
import com.ib.cat.entity.BoardReply;
import com.ib.cat.repository.BoardReplyRepository;
import com.ib.cat.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardReplyService {
    @Autowired
    BoardReplyRepository boardReplyRepository;

    @Autowired
    BoardRepository boardRepository;

    public void insertComment(BoardReply boardReply) {
        boardReplyRepository.save(boardReply);

        int no = boardReply.getBoardNo();
        Board board = boardRepository.findByNo(no);
        board.setReplys(board.getReplys()+1);
        boardRepository.save(board);
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
        BoardReply boardReply = boardReplyRepository.findById(no).get();
        boardReplyRepository.delete(boardReply);

        int boardNo = boardReply.getBoardNo();
        Board board = boardRepository.findByNo(boardNo);
        board.setReplys(board.getReplys()-1);
        boardRepository.save(board);
    }

    public BoardReply findByNo(int no){return boardReplyRepository.findById(no).get();}
    public List<BoardReply> commentCheck(int step){
        return boardReplyRepository.findByStep(step);
    }
}
