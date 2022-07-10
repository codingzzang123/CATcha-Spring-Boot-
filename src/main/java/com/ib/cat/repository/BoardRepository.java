package com.ib.cat.repository;

import com.ib.cat.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query(value = "select * from BOARD WHERE ROWNUM <= 5 order by NO desc", nativeQuery = true)
    public List<Board> getBoardTop();

}
