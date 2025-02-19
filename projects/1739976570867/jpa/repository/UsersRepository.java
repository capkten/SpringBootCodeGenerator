package com.hzy.repository;
import com.hzy.entity.Users;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @description users
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {



}
