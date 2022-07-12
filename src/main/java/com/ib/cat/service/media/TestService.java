package com.ib.cat.service.media;

import com.ib.cat.controller.main.LikeMediaDto;
import com.ib.cat.entity.Test;
import com.ib.cat.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public void insert(LikeMediaDto likeMediaDto){

        Test test = new Test();
        test.setName(likeMediaDto.getName()); test.setNum(likeMediaDto.getNum()); test.setOverview(likeMediaDto.getOverview());
        test.setTitle(likeMediaDto.getTitle()); test.setPasterPath(likeMediaDto.getPoster()); test.setCode(likeMediaDto.getCode());

        testRepository.save(test);
    }

    public void delete(int contentNum){
        Test test = testRepository.findByNum(contentNum);

        if(test != null){
            testRepository.delete(test);
        }
    }
}
