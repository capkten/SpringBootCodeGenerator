package com.hzy.repository;
import com.hzy.entity.Admin;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @description admin
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {



}
