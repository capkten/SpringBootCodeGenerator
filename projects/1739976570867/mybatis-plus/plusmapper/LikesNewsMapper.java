package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.LikesNews;
import java.util.List;
/**
 * @description likes_newsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface LikesNewsMapper extends BaseMapper<LikesNews> {

    @Select(
    "<script>select t0.* from likes_news t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<LikesNews> pageAll(LikesNews queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from likes_news t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
     " </script>")
    int countAll(LikesNews queryParamDTO);

}
