package com.ib.cat.service.info;

import com.ib.cat.entity.Like;
import com.ib.cat.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberLikeService {

    @Autowired
    LikeRepository likeRepository;

    public List<Like> getMovie(String memberId,int code){
        return likeRepository.findAllByNameAndCode(memberId,code);
    }

    public List<Like> getTv(String memberId, int code){
        return likeRepository.findAllByNameAndCode(memberId,code);
    }

    public List<Like> getBoard(String memberId, int code){
        return likeRepository.findAllByNameAndCode(memberId,code);
    }
}
