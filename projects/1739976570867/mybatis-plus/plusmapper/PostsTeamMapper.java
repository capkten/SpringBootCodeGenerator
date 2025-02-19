package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.PostsTeam;
import java.util.List;
/**
 * @description posts_teamMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface PostsTeamMapper extends BaseMapper<PostsTeam> {

    @Select(
    "<script>select t0.* from posts_team t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<PostsTeam> pageAll(PostsTeam queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from posts_team t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
     " </script>")
    int countAll(PostsTeam queryParamDTO);

}
