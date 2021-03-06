package com.ib.cat.repository;

import com.ib.cat.entity.BoardReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardReplyRepository extends JpaRepository<BoardReply, Integer> {

    public List<BoardReply> findByWriterOrderByNoDesc(String name);

    public Long countByWriter(String name);

    public List<BoardReply> findByRefAndBoardNoOrderByNoAsc(int ref,int boardNo); //게시물에대한 댓글 가져오는 메소드

    public List<BoardReply> findByRefAndBoardNoOrderByRegdateAsc(int ref, int boardNo); //대댓글 가져오는 메소드
    List<BoardReply> findByStep(int step);

//    public void deleteByNo(int no);
}
