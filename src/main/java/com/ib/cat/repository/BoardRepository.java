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

    /* 최근 게시물 5개 불러오기
    * 누구는 findTop 메소드로 할 경우 오류가 발생 됨(해결 방법 못찾음)
    * Oracle 11g 에서는 안됨!!  */
    @Query(value = "select * from (select * from board order by No desc) where ROWNUM <= 5", nativeQuery = true)
    public List<Board> getBoardTop();

    public List<Board> findTop5ByOrderByNoDesc();

    public List<Board> findByTitleContainingOrContentContainingIgnoreCase(String query,String query2);

    public Integer countByTitleContainingOrContentContainingIgnoreCase(String query,String query2);
    public Integer countByName(String name);
    public List<Board> findByName(String name);

    //1번
    public List<Board> findAllByOrderByNoDesc();
    //서치 이름+페이징
    Page<Board> findByNameContaining (String searchKeyword, Pageable pageable);

    //서치 제목+페이징
    Page<Board> findByTitleContaining (String searchKeyword, Pageable pageable);

    //페이징
    Page<Board> findAll(Pageable pageable);







}
