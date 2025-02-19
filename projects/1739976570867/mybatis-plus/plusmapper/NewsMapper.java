package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.News;
import java.util.List;
/**
 * @description newsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {

    @Select(
    "<script>select t0.* from news t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<News> pageAll(News queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from news t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
     " </script>")
    int countAll(News queryParamDTO);

}
