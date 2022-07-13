package com.ib.cat.repository;

import com.ib.cat.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

}
