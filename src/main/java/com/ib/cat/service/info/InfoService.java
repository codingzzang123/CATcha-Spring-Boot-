package com.ib.cat.service.info;

import com.ib.cat.dto.Info.InfoCountDto;
import com.ib.cat.dto.Info.ListBoardDto;
import com.ib.cat.dto.Info.ListMediaDto;
import com.ib.cat.entity.Board;
import com.ib.cat.entity.ContentReply;
import com.ib.cat.repository.BoardRepository;
import com.ib.cat.repository.MediaReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MediaReplyRepository mediaReplyRepository;

    public InfoCountDto infoCountDto(String name,String id){ // contentReply fk가 member id라서, fk member name으로 바꾸면 빼야함
        InfoCountDto infoCountDto = new InfoCountDto();

        infoCountDto.setBoard(boardRepository.countByName(name));
        infoCountDto.setReview(mediaReplyRepository.countByWriter(id)); // media레포에 Long으로 반환 하고 있음 -> dto 컬럼 Long

        return infoCountDto;
    }

    public List<ListBoardDto> getBoards(String name){
        List<Board> tmp = boardRepository.findByName(name);
        List<ListBoardDto> results = new ArrayList<>();
        if(tmp != null) {
            for (Board board : tmp) {
                ListBoardDto lbd = new ListBoardDto(
                        board.getNo(), board.getTitle(), board.getCate(), board.getRegdate()
                );
                results.add(lbd);
            }
        }
        return results;
    }

    public List<ListMediaDto> getMedias(String id){
        List<ContentReply> tmp = mediaReplyRepository.findByWriter(id);
        List<ListMediaDto> results = new ArrayList<>();

        for(ContentReply contentReply : tmp){
            ListMediaDto lmd = new ListMediaDto(
                    contentReply.getContent(), contentReply.getWriter(), contentReply.getCode(),
                    contentReply.getContentsNum(),contentReply.getRegdate()
            );
            results.add(lmd);
        }
        return results;
    }

}
