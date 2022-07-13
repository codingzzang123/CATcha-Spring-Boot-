//package com.ib.cat.service.media;
//
//import com.ib.cat.dto.media.LikeDto;
//import com.ib.cat.entity.Test;
//import com.ib.cat.repository.TestRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TestService {
//
//    @Autowired
//    TestRepository testRepository;
//
//    public void insert(LikeDto likeDto){
//
//        Test test = new Test();
//        test.setName(likeDto.getName()); test.setNum(likeDto.getNum()); test.setOverview(likeDto.getOverview());
//        test.setTitle(likeDto.getTitle()); test.setPasterPath(likeDto.getPoster()); test.setCode(likeDto.getCode());
//
//        testRepository.save(test);
//    }
//
//    public void delete(int contentNum){
//        Test test = testRepository.findByNum(contentNum);
//
//        if(test != null){
//            testRepository.delete(test);
//        }
//    }
//}
