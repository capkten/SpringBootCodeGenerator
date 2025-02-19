package com.hzy.repository;
import com.hzy.entity.League;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @description league
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public interface LeagueRepository extends JpaRepository<League,Integer> {



}
