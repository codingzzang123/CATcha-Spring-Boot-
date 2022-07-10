package com.ib.cat.repository;

import com.ib.cat.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    public int countById(String id);
    public int countByName(String name);
}
