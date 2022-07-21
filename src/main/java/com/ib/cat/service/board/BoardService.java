package com.ib.cat.service.board;

import com.ib.cat.entity.Board;
import com.ib.cat.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    public List<Board> getAll(){
        return  boardRepository.findAllByOrderByNoDesc();
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

    public Board getOne(int id) {
        Board board = boardRepository.findById(id).get();
        return board;
    }





}
