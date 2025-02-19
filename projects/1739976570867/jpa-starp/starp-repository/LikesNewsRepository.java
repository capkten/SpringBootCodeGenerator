package com.hzy.repository;
import com.hzy.entity.LikesNews;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @description likes_news
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public interface LikesNewsRepository extends JpaRepository<LikesNews,Integer> {



}
