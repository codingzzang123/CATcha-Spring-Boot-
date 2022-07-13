package com.ib.cat.repository;

import com.ib.cat.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LikeRepository extends JpaRepository<Like,Long> {

    //CRUD!

    Like findByContentsNumAndCode(int contentsNum, int code);
    Like findByNameAndContentsNum(String name, int contentsNum);

//    public int find

}
