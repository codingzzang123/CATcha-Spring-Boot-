package com.ib.cat.repository;

import com.ib.cat.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
    /* 1. 유저 이름을 받아 카운트 (int)받아오기 */
    public Integer countBySubName(String subName);

    /* 2. 좋아요 찾을 때 사용하는 메소드 */
    public Alert findBySubNameAndPubNameAndBnoAndCode(String subName,String pubName,
                                                      Integer bno, Integer code);

    /* 3. 댓글 찾을 때 사용하는 메소드
    * 나중에 추가 될 가능성 있음 */
//    public Alert findBySubNameAndPubNameAndBnoAndCodeAndReplyCode(String subName,String pubName,
//                                                      Integer bno, Integer code, Integer replyCode);

    public List<Alert> findBySubNameOrderByNoDesc(String name);

}
