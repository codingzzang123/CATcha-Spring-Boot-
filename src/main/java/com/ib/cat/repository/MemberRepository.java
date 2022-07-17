package com.ib.cat.repository;

import com.ib.cat.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    int countById(String id);
    int countByName(String name);

    Member findByName(String name);
}
