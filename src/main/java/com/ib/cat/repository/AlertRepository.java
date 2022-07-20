package com.ib.cat.repository;

import com.ib.cat.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
    /* 1. 유저 이름을 받아 카운트 (int)받아오기 */
    public Integer countBySubName(String subName);

    public Alert findBySubNameAndPubNameAndBnoAndCode(String subName,String pubName,
                                                      Integer bno, Integer code);

    public List<Alert> findBySubNameOrderByNoDesc(String name);

}
