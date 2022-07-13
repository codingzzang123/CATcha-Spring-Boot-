package com.ib.cat.service.media;

import com.ib.cat.dto.media.LikeDto;
import com.ib.cat.entity.Like;
import com.ib.cat.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    @Autowired
    private final LikeRepository likeRepository;

    //좋아요 add!!
    public void addLike(LikeDto likeDto) { //이미 좋아요 한건지 확인해야함
        Like like = new Like();
        like.setName(likeDto.getName());
        like.setContentsNum(likeDto.getNum());
        like.setPosterPath(likeDto.getPoster());
        like.setTitle(likeDto.getTitle());
        like.setOverview(likeDto.getOverview());
        like.setCode(likeDto.getCode());
        likeRepository.save(like);
    }
    //좋아요 cancel!!
    public void cancelLike(String name, int contentsNum, int code) {

        Like like = likeRepository.findByNameAndContentsNumAndCode(name, contentsNum,code);
        if (like != null) {
            likeRepository.delete(like);
        }

    }
    /*  id 기준으로 좋아요 한 contents인지 확인
        1. contentsController에서 활용 2. Board에서도 활용 */
    public String checkLike(LikeDto likeDto) {
        String flag = "false";
        //id&contentsNum&code으로 조회했을 때, 있으면 entity 반환
        if (likeRepository.findByNameAndContentsNumAndCode(
                likeDto.getName(),likeDto.getNum(), likeDto.getCode()) != null) {
            flag = "true"; //좋아요 O
        } else {
            flag= "false"; //좋아요 X
        }
        return flag;
    }

}
