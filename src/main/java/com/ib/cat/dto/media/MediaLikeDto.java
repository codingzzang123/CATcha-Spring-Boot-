package com.ib.cat.dto.media;

import com.ib.cat.entity.Member;
import lombok.Data;

@Data
public class MediaLikeDto {
    
    private int contentsNum; //컨텐츠번호(id)
    private Member member; //좋아요 누른 member객체
    
}
