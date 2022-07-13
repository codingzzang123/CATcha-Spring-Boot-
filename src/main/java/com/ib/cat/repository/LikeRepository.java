package com.ib.cat.repository;

import com.ib.cat.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    //CRUD!
    Like findByNameAndContentsNum(String name, int contentsNum);
    Like findByNameAndContentsNumAndCode(String name, int contentsNum, int code);

    /* info -> memberlike */
    List<Like> findAllByNameAndCode(String memberId, int code);
    Integer countByNameAndCode(String memberId, int code);

}
