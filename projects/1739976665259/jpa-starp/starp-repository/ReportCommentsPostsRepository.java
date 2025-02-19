package com.hzy.repository;
import com.hzy.entity.ReportCommentsPosts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public interface ReportCommentsPostsRepository extends JpaRepository<ReportCommentsPosts,Integer> {



}
