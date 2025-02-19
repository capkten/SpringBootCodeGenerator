package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.CommentsNews;
import java.util.List;
/**
 * @description comments_newsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface CommentsNewsMapper extends BaseMapper<CommentsNews> {

    @Select(
    "<script>select t0.* from comments_news t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<CommentsNews> pageAll(CommentsNews queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from comments_news t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
     " </script>")
    int countAll(CommentsNews queryParamDTO);

}
