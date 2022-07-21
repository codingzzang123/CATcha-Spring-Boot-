package com.ib.cat.service.board;

import com.ib.cat.dto.Board.BoardDetailDto;
import com.ib.cat.entity.Board;
import com.ib.cat.entity.Member;
import com.ib.cat.repository.BoardRepository;
import com.ib.cat.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    MemberRepository memberRepository;

    public List<Board> getAll(){
        return  boardRepository.findAllByOrderByNoDesc();
    }
    public Page<Board> list(int page){
        return boardRepository.findAll(PageRequest.of(
                page, 20, Sort.by(Sort.Direction.DESC, "no")));
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



}
