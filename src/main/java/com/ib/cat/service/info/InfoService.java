package com.ib.cat.service.info;

import com.ib.cat.dto.Info.InfoCountDto;
import com.ib.cat.dto.Info.ListBoardDto;
import com.ib.cat.entity.Board;
import com.ib.cat.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoService {

    @Autowired
    BoardRepository boardRepository;

    public InfoCountDto infoCountDto(String name){
        InfoCountDto infoCountDto = new InfoCountDto();

        infoCountDto.setBoard(boardRepository.countByName(name));

        return infoCountDto;
    }

    public List<ListBoardDto> getBoards(String name){
        List<Board> tmp = boardRepository.findByName(name);
        List<ListBoardDto> results = new ArrayList<>();
        for(Board board : tmp){
            ListBoardDto lbd = new ListBoardDto(
                    board.getNo(),board.getTitle(),board.getCate(),board.getRegdate()
            );
            results.add(lbd);
        }
        return results;
    }

}
