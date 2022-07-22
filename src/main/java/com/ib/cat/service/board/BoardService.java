package com.ib.cat.service.board;

import com.ib.cat.dto.Board.BoardDetailDto;
import com.ib.cat.entity.Board;
import com.ib.cat.entity.Member;
import com.ib.cat.repository.BoardRepository;
import com.ib.cat.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    //페이징
    public Page<Board> findAll(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    //서치 이름 + 페이징
    public Page<Board> search1(String searchKeyword, Pageable pageable){
        return boardRepository.findByNameContaining(searchKeyword, pageable);
    }

    //서치 제목 + 페이징
    public Page<Board> search2(String searchKeyword, Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    public void insert(Board board){
        boardRepository.save(board);
    }


    public void update(Board board){
        boardRepository.save(board);
    }

    public void delete(Board board){
        boardRepository.delete(board);
    }

    public BoardDetailDto getBoard(int id){
        Board board = boardRepository.findById(id).get();
        Member member = memberRepository.findByName(board.getName());

        BoardDetailDto result = new BoardDetailDto(
                board.getNo(),board.getName(),board.getCate(),board.getTitle(),
                board.getContent(),board.getRegdate(),board.getViews(),
                board.getLikes(),board.getReplys(),member.getImgs()
        );
        return result;
    }

    public Board findBoard(int id){
        Board board = boardRepository.findById(id).get();
        return board;
    }

    public void views(int no){
        Board board = boardRepository.findById(no).get();
        board.setViews(board.getViews()+1);
        boardRepository.save(board);
    }


}
