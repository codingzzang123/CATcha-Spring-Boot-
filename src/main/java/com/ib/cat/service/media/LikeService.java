package com.ib.cat.service.media;

import com.ib.cat.dto.media.LikeDto;
import com.ib.cat.entity.Like;
import com.ib.cat.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public void cancelLike(int contentsNum, int code) {
        Like like = likeRepository.findByContentsNumAndCode(contentsNum,code);
        if (like != null) {
            likeRepository.delete(like);
        }

    }
    //id 기준으로 좋아요 한 contents인지 확인 - 1. contentsController에서 활용 2. Board에서도 활용
    public boolean checkLike(LikeDto likeDto) {
        boolean flag = false;
        //id&contentsNum으로 조회했을 때, 있으면 entity 반환
        if (likeRepository.findByNameAndContentsNum(likeDto.getName(), likeDto.getCode()) != null) {
            flag = true; //좋아요 O
        } else {
            flag=false; //좋아요 X
        }
        return flag;
    }

    //id 기준으로 좋아요한 contents List 반환 - 관심목록보기에서 활용
//    public List<MediaLike> checkAllMediaLike(MediaLike mediaLike) {
//        List<MediaLike> list = null;
//        if(mediaLikeRepository.)
//
//    }
}
