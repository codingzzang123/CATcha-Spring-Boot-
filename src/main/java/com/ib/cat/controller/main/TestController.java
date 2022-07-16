package com.ib.cat.controller.main;


import com.ib.cat.entity.Board;
import com.ib.cat.entity.BoardReply;
import com.ib.cat.repository.BoardReplyRepository;
import com.ib.cat.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    BoardReplyRepository reply;
    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/tttt")
    public String test(){

        /* 1. 전체 댓글 (댓글만) 가져오기 */
        List<BoardReply> test1 = reply.findByRefAndBoardNoOrderByNoDesc(0,34);
        for(BoardReply b : test1)
            System.out.println(b.toString());

        /* 2. 전체 댓글 (답글만) 가져오기 */
        List<BoardReply> test2 = reply.findByRefAndBoardNoOrderByDepthDesc(1,34);
        for(BoardReply b: test2)
            System.out.println(b.toString());

        /* 3. 34번 글에 대한 댓글 생성  -> 34번 글 댓글 수 1 증가 */
        BoardReply test3 = new BoardReply();
        test3.setWriter("wendy"); test3.setBoardNo(34); test3.setContent("대댓글1");
        test3.setDepth(0);
        reply.save(test3);

        Optional<Board> board = boardRepository.findById(test3.getBoardNo());
        board.ifPresent(e->{
            e.setViews(e.getViews()+1);
        }); boardRepository.save(board.get());
        System.out.println("댓글 생성 완료");

        /* 3. 34번 글에 대한 대댓글 생성 */
        BoardReply test4 = new BoardReply();
        test4.setWriter("wendy"); test4.setBoardNo(34); test4.setContent("대댓글1");
        test4.setRef(1); test4.setStep(4); //
        reply.save(test4);

        Optional<Board> board2 = boardRepository.findById(test4.getBoardNo());
        board2.ifPresent(e->{
            e.setViews(e.getViews()+1);
        }); boardRepository.save(board2.get());
        System.out.println("대댓글 생성 완료");

        /* 대댓글 생성할때 step부분에 부모댓글 no (pk) 값이 와야 함 (지금은 임의로 넣어준것) */

        return "테스트중";
    }
}
