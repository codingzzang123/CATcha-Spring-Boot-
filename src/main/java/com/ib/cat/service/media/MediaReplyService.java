package com.ib.cat.service.media;

import com.ib.cat.dto.media.ContentReplyDto;
import com.ib.cat.entity.ContentReply;
import com.ib.cat.repository.MediaReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MediaReplyService {
    @Autowired
    MediaReplyRepository mediaReplyRepository;

    //*      MediaContentController       *//
    /*  contents - 리뷰 로딩 (contentsService랑 같이 작동) */
    public List<ContentReply> getMediaReplyPage(int code, int contentsNum)  {
        List<ContentReply> contentReplies
                = mediaReplyRepository.findByContentsNumAndCode(code, contentsNum);
        //            tmpReplies.set(0,new ContentReply(1, contentsNum, "default", "default",new Timestamp(System.currentTimeMillis()) ,code));

        return contentReplies;
    }
    //*      MediaReplyController     *//

    /*  content 상세 페이지  :  리뷰 쓰기 (로그인 한 사람)  */
//    public void writeMediaReply(ContentReply reply) {
//        System.out.println(reply.getContent());
//        System.out.println(reply.getContentsNum());
//        System.out.println("db");
//        mediaReplyRepository.save(reply);
//    }
    public void writeMediaReply(int contentsNum, String writer, String content, int code) {
        ContentReply contentReply = new ContentReply(contentsNum, writer, content, code);
        mediaReplyRepository.save(contentReply);
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

}
