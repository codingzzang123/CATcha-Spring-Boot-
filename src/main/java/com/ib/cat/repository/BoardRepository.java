package com.ib.cat.repository;

import com.ib.cat.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

//    @Query(value = "select * from (select * from board order by No desc) where ROWNUM <= 5", nativeQuery = true)
//    public List<Board> getBoardTop();
    public Board findByNo(Integer no);
    public List<Board> findTop5ByOrderByNoDesc();

    public List<Board> findByTitleContainingOrContentContainingIgnoreCase(String query,String query2);

    public Integer countByTitleContainingOrContentContainingIgnoreCase(String query,String query2);

    public Integer countByName(String name);

    public List<Board> findByName(String name);

    //서치 이름+페이징

    //서치 제목+페이징

    //페이징
    Page<Board> findAll(Pageable pageable);

    Page<Board> findByContentContainingOrTitleContainingIgnoreCase(String keyword, String keyword2, Pageable pageable);

    Page<Board> findByTitleContaining (String searchKeyword, Pageable pageable);

    Page<Board> findByContentContaining(String keyword, Pageable pageable);

    Page<Board> findByNameContaining (String searchKeyword, Pageable pageable);

}
