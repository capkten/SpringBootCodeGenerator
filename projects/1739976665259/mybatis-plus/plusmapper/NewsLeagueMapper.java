package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.NewsLeague;
import java.util.List;
/**
 * @description news_leagueMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface NewsLeagueMapper extends BaseMapper<NewsLeague> {

    @Select(
    "<script>select t0.* from news_league t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='nId!=null and nId!=&apos;&apos; '> and t0.n_id=#{nId}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<NewsLeague> pageAll(NewsLeague queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from news_league t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='nId!=null and nId!=&apos;&apos; '> and t0.n_id=#{nId}</when> " +
     " </script>")
    int countAll(NewsLeague queryParamDTO);

}
