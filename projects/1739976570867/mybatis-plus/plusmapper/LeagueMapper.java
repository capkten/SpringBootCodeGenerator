package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.League;
import java.util.List;
/**
 * @description leagueMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface LeagueMapper extends BaseMapper<League> {

    @Select(
    "<script>select t0.* from league t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<League> pageAll(League queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from league t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
     " </script>")
    int countAll(League queryParamDTO);

}
