package com.hzy.repository;
import com.hzy.entity.NewsEditor;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @description news_editor
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public interface NewsEditorRepository extends JpaRepository<NewsEditor,Integer> {



}
