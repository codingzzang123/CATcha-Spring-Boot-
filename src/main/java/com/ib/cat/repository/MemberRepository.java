package com.ib.cat.repository;

import com.ib.cat.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    public int countById(String id);
    public int countByName(String name);
}
