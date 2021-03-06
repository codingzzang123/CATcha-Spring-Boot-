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

    public List<Like> getList(String memberId, int code){
        return likeRepository.findAllByNameAndCode(memberId,code);
    }
    public int getCount(String memberId, int code){
        return likeRepository.countByNameAndCode(memberId,code);
    }
}
