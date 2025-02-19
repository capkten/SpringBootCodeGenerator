package com.hzy.mapper;
import com.hzy.entity.NewsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
* @description newsMapper
* @author capkin
* @date 2025-02-19
*/
@Mapper
public interface NewsDao extends BaseMapper<NewsEntity> {

}