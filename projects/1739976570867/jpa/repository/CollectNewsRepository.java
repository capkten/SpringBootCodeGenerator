package com.hzy.repository;
import com.hzy.entity.CollectNews;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @description collect_news
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public interface CollectNewsRepository extends JpaRepository<CollectNews,Integer> {



}
