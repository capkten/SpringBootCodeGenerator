package com.hzy.repository;
import com.hzy.entity.PostsTeam;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @description posts_team
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public interface PostsTeamRepository extends JpaRepository<PostsTeam,Integer> {



}
