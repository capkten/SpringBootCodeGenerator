package com.hzy.repository;
import com.hzy.entity.CommentsNews;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @description comments_news
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public interface CommentsNewsRepository extends JpaRepository<CommentsNews,Integer> {



}
