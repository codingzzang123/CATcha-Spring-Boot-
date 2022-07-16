package com.ib.cat.repository;

import com.ib.cat.dto.media.ContentReplyDto;
import com.ib.cat.entity.ContentReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaReplyRepository extends JpaRepository<ContentReply, Long> {

    List<ContentReply> findByContentsNumAndCode(int contentsNum, int code); //type=code
    List<ContentReply> findByWriter(String id);
    List<ContentReply> findByWriterAndCode(String id, int code); //type=code
    Long countByWriter(String id);
    Long countByWriterAndCode(String id, int code);
//    int countByContentsNum(int contentsNum);

    ContentReply findFirstByContentsNumAndAndCodeOrderByNoDesc(int contentsNum, int code);

}
