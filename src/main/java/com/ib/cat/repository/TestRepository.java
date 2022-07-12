package com.ib.cat.repository;

import com.ib.cat.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test,Integer> {

    public Test findByNum(int contentNum);

}
