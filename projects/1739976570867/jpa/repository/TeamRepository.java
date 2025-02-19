package com.hzy.repository;
import com.hzy.entity.Team;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @description team
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {



}
