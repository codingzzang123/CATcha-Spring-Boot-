package com.ib.cat.service.media;

import com.ib.cat.dto.media.MediaLikeDto;
import com.ib.cat.repository.MediaLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MediaLikeService {
    private final MediaLikeRepository mediaLikeRepository;

    public Boolean pushLikeButton(MediaLikeDto mediaLikeDto) {
//        mediaLikeRepository.findAllById(mediaLikeDto.getMember().getId());
return true;
    }
}
