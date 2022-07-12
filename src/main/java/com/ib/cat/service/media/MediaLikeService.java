package com.ib.cat.service.media;

import com.ib.cat.dto.media.MediaLikeDto;
import com.ib.cat.entity.MediaLike;
import com.ib.cat.repository.MediaLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MediaLikeService {
    private final MediaLikeRepository mediaLikeRepository;

//    public Boolean pushLikeButton(MediaLikeDto mediaLikeDto) {
////        mediaLikeRepository.findAllById(mediaLikeDto.getMember().getId());
//return true;
//    }
    //좋아요 누름
    public void mediaLikeInsert(MediaLike mediaLike) { //이미 좋아요 한건지 확인해야함
        mediaLikeRepository.save(mediaLike);
    }
    //좋아요 취소
    public void mediaLikeDelete(MediaLike mediaLike) {
        mediaLikeRepository.delete(mediaLike);
    }
    //id 기준으로 좋아요 한 contents인지 확인 - MediaContentsController에서 활용
    public int checkMediaLike(MediaLike mediaLike) {
        int result=0;
        if (mediaLikeRepository.findById(Long.valueOf(mediaLike.getMember().getId())).isPresent()) {
            result = 1; //있으면 1
        } else result=0; //없으면 0
        return result;
    }

    //id 기준으로 좋아요한 contents List 반환 - 관심목록보기에서 활용
//    public List<MediaLike> checkAllMediaLike(MediaLike mediaLike) {
//        List<MediaLike> list = null;
//        if(mediaLikeRepository.)
//
//    }
}
