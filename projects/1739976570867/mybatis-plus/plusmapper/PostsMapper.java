package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.Posts;
import java.util.List;
/**
 * @description postsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface PostsMapper extends BaseMapper<Posts> {

    @Select(
    "<script>select t0.* from posts t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<Posts> pageAll(Posts queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from posts t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
     " </script>")
    int countAll(Posts queryParamDTO);

}
