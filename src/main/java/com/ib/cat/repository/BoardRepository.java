package com.ib.cat.repository;

import com.ib.cat.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query(value = "select * from (select * from board order by No desc) where ROWNUM <= 5", nativeQuery = true)
    public List<Board> getBoardTop();

    public List<Board> findTop5ByOrderByNoDesc();

}
