package com.ib.cat.service.media;

import com.ib.cat.dto.media.ContentReplyDto;
import com.ib.cat.entity.ContentReply;
import com.ib.cat.repository.MediaReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaReplyService {
    @Autowired
    MediaReplyRepository mediaReplyRepository;

    //*      MediaContentController       *//
    /*  contents - 리뷰 로딩 (contentsService랑 같이 작동) */
    public List<ContentReplyDto> getMediaReplyPage(int contentsNum, int code)  {
        List<ContentReply> tmp = mediaReplyRepository.findByContentsNumAndCode(contentsNum, code);
        System.out.println("Servie - List<ContentReply> tmp:"+tmp);
        List<ContentReplyDto> list = new ArrayList<>();

        for (ContentReply contentReply : tmp) {
            ContentReplyDto crd = new ContentReplyDto(
                    contentReply.getContentsNum(),
                    contentReply.getWriter(), contentReply.getContent(),
                    contentReply.getTitle(),
                    contentReply.getCode()
            );
            list.add(crd);
        }
        return list;
    }
    //*      MediaReplyController     *//
    /*  content 상세 페이지  :  리뷰 쓰기 (로그인 한 사람)  */
    public void writeMediaReply(ContentReplyDto dto) {
        ContentReply result = new ContentReply();
           result.setContentsNum(dto.getContentsNum());
           result.setWriter(dto.getWriter());
           result.setTitle(dto.getTitle());
           result.setContent(dto.getContent());
           result.setCode(dto.getCode());

        mediaReplyRepository.save(result);
    }



//    /*  content 상세 페이지  :  리뷰 수정   */
//    public void modifyMediaReply(ContentReply reply) {
//        mediaReplyRepository.save(reply);
//    }
//    /*  content 상세 페이지  :  리뷰 삭제   */
//    public void deleteMediaReply(ContentReply reply){
//        mediaReplyRepository.delete(reply);
//    }

    //*     infoController     *//
    /*   info : 사용자별 작성 리뷰 로딩 (전체)   */
    public List<ContentReply> getMediaReplyList(String id) {
        List<ContentReply> contentReplies
                = mediaReplyRepository.findByWriter(id);
        return contentReplies;
    }

    /*   info : 사용자별 작성 리뷰 로딩 (type-code별)   */
    public List<ContentReply> getMediaReplyListByCode(String id, int code) {
        List<ContentReply> contentReplies
                =mediaReplyRepository.findByWriterAndCode(id, code);
        return contentReplies;
    }

    /*   info : 사용자별 작성 리뷰 갯수    */
    public Long getMediaReplyCount(String id) {
        Long count = mediaReplyRepository.countByWriter(id);
        return count;
    }

    /*   info : 사용자별 작성 리뷰 갯수 by type   */
    public Long getMediaReplyCountByCode(String id, int code) {
        Long count = mediaReplyRepository.countByWriterAndCode(id,code);
        return count;
    }

    /* 호선 추가 */
    public ContentReplyDto getMediaReply(int contentsNum, int code)  {
        ContentReply tmp = mediaReplyRepository.findFirstByContentsNumAndAndCodeOrderByNoDesc(contentsNum, code);
        System.out.println("ContentReply tmp:"+tmp);
        ContentReplyDto list = new ContentReplyDto(
          tmp.getContentsNum(),tmp.getWriter(),tmp.getContent(),
          tmp.getTitle(),tmp.getCode()
        );

        return list;
    }
}
