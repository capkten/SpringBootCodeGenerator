package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.NewsTeam;
import java.util.List;
/**
 * @description news_teamMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface NewsTeamMapper extends BaseMapper<NewsTeam> {

    @Select(
    "<script>select t0.* from news_team t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='nId!=null and nId!=&apos;&apos; '> and t0.n_id=#{nId}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<NewsTeam> pageAll(NewsTeam queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from news_team t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='nId!=null and nId!=&apos;&apos; '> and t0.n_id=#{nId}</when> " +
     " </script>")
    int countAll(NewsTeam queryParamDTO);

}
