package com.ib.cat.repository;

import com.ib.cat.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

//    public List<Board> findTop5ByNoDesc();
    public List<Board> findTop5ByOrderByNoDesc();

//    @Query("select r from board r where r.reg = :date")
//    public List<Board> customFind();
}
