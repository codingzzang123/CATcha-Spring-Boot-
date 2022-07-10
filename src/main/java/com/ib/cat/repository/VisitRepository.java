package com.ib.cat.repository;

import com.ib.cat.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {
    Integer countAllBy();

    @Query("select count (r) from visit r where r.reg = :date")
    Long countToday(@Param("date") LocalDate date);
}
