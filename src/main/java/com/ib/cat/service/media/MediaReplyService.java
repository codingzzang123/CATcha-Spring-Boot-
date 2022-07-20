package com.ib.cat.service.media;

//import com.ib.cat.dto.media.ContentReplyDto;
import com.ib.cat.dto.media.ReviewDto;
import com.ib.cat.entity.ContentReply;
import com.ib.cat.entity.Member;
import com.ib.cat.repository.MediaReplyRepository;
import com.ib.cat.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediaReplyService {
    @Autowired
    MediaReplyRepository mediaReplyRepository;

    @Autowired
    MemberRepository memberRepository;
//
//    //*     infoController     *//
//    /*   info : 사용자별 작성 리뷰 로딩 (전체)   */
//    public List<ContentReply> getMediaReplyList(String id) {
//        List<ContentReply> contentReplies
//                = mediaReplyRepository.findByWriter(id);
//        return contentReplies;
//    }
//
//    /*   info : 사용자별 작성 리뷰 로딩 (type-code별)   */
//    public List<ContentReply> getMediaReplyListByCode(String id, int code) {
//        List<ContentReply> contentReplies
//                =mediaReplyRepository.findByWriterAndCode(id, code);
//        return contentReplies;
//    }
//
//    /*   info : 사용자별 작성 리뷰 갯수    */
//    public Long getMediaReplyCount(String id) {
//        Long count = mediaReplyRepository.countByWriter(id);
//        return count;
//    }
//
//    /*   info : 사용자별 작성 리뷰 갯수 by type   */
//    public Long getMediaReplyCountByCode(String id, int code) {
//        Long count = mediaReplyRepository.countByWriterAndCode(id,code);
//        return count;
//    }
//
//    /* 호선 추가  */
    /* 1. DB 저장 */
    public void insert(ReviewDto reviewDto){
        ContentReply contentReply = new ContentReply();
        contentReply.setContentsNum(reviewDto.getContentsNum()); contentReply.setWriter(reviewDto.getWriter());
        contentReply.setTitle(reviewDto.getTitle()); contentReply.setContent(reviewDto.getContent());
        contentReply.setCode(reviewDto.getCode()); contentReply.setRating(reviewDto.getRating());

        mediaReplyRepository.save(contentReply);
    }
    /* 2. Select */
    public List<ReviewDto> getReviews(int contentsNum, int code){
        List<ReviewDto> list = new ArrayList<>();
        List<ContentReply> tmp = mediaReplyRepository.findByContentsNumAndCodeOrderByNoDesc(contentsNum,code);

        for (ContentReply contentReply : tmp){
            Member member = memberRepository.findByName(contentReply.getWriter());
            String img = member.getImgs();

            ReviewDto reviewDto = new ReviewDto(
                    contentReply.getNo(),contentReply.getContentsNum(),contentReply.getWriter(),
                    contentReply.getContent(),contentReply.getTitle(),contentReply.getNo(),img,
                    contentReply.getRegdate(), contentReply.getRating()
            ); list.add(reviewDto);
        }

        return list;
    }
    /* 3. Delete */
    public void delete(int no){
        Optional<ContentReply> contentReply = mediaReplyRepository.findById(no);
        if(contentReply.isPresent())
            mediaReplyRepository.delete(contentReply.get());
    }

    public void insert(String content, String title, String writer, String img, int contentsNum, int code, int rating){
        ContentReply result = new ContentReply();
        result.setTitle(title); result.setContentsNum(contentsNum); result.setWriter(writer);
        result.setContent(content); result.setCode(code); result.setImg(img); result.setRating(rating);

        mediaReplyRepository.save(result);
    }

    public double getRatingAvg(int contentsNum, int code) {
        List<ContentReply> replyList = mediaReplyRepository.findByContentsNumAndCodeOrderByNoDesc(contentsNum, code);
        double sum=0;
        double result = 0;
        for (int i = 0 ; i < replyList.size() ; i++) {
            sum += replyList.get(i).getRating();
        }
        double count = replyList.size();

        if (count != 0){
            result = Math.round(sum/count*100)/100.0;
        }
        return result;

    }
}
