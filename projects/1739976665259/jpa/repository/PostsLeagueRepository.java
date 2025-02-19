package com.hzy.repository;
import com.hzy.entity.PostsLeague;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @description posts_league
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public interface PostsLeagueRepository extends JpaRepository<PostsLeague,Integer> {



}
