package com.ib.cat.service.info;

import com.ib.cat.dto.Info.InfoCountDto;
import com.ib.cat.dto.Info.ListBoardDto;
import com.ib.cat.dto.Info.ListMediaDto;
import com.ib.cat.dto.Info.ListReplyDto;
import com.ib.cat.entity.Board;
import com.ib.cat.entity.BoardReply;
import com.ib.cat.entity.ContentReply;
import com.ib.cat.repository.BoardReplyRepository;
import com.ib.cat.repository.BoardRepository;
import com.ib.cat.repository.MediaReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InfoService {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MediaReplyRepository mediaReplyRepository;

    @Autowired
    BoardReplyRepository boardReplyRepository;

    public InfoCountDto infoCountDto(String name){ // contentReply fk가 member id라서, fk member name으로 바꾸면 빼야함
        InfoCountDto infoCountDto = new InfoCountDto();

        infoCountDto.setBoard(boardRepository.countByName(name));
        infoCountDto.setReview(mediaReplyRepository.countByWriter(name)); // media레포에 Long으로 반환 하고 있음 -> dto 컬럼 Long
        infoCountDto.setReply(boardReplyRepository.countByWriter(name));
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

    public List<ListReplyDto> getReplys(String name){
        List<BoardReply> tmp = boardReplyRepository.findByWriterOrderByNoDesc(name);
        List<ListReplyDto> results = new ArrayList<>();

        for(BoardReply boardReply : tmp){
            Optional<Board> getBoard = boardRepository.findById(boardReply.getBoardNo());
            Board board = new Board();
            if(getBoard.isPresent())
                board = getBoard.get();

            ListReplyDto lrd = new ListReplyDto(
                    boardReply.getBoardNo(),board.getTitle(),board.getCate(),
                    boardReply.getContent(),boardReply.getRegdate()
            );
            results.add(lrd);
        }
        return results;
    }

    public List<ListMediaDto> getMedias(String name){
        List<ContentReply> tmp = mediaReplyRepository.findByWriter(name);
        List<ListMediaDto> results = new ArrayList<>();

        for(ContentReply contentReply : tmp){
            ListMediaDto lmd = new ListMediaDto(
                    contentReply.getContent(), contentReply.getWriter(), contentReply.getCode(),
                    contentReply.getContentsNum(),contentReply.getRegdate(),contentReply.getTitle()
            );
            results.add(lmd);
        }
        return results;
    }

}
