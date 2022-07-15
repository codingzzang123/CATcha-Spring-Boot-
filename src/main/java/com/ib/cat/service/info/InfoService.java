package com.ib.cat.service.info;

import com.ib.cat.dto.Info.InfoCountDto;
import com.ib.cat.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Autowired
    BoardRepository boardRepository;

    public InfoCountDto infoCountDto(String name){
        InfoCountDto infoCountDto = new InfoCountDto();

        infoCountDto.setBoard(boardRepository.countByName(name));

        return infoCountDto;
    }

}
