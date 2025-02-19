package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.ReportPosts;
import java.util.List;
/**
 * @description report_postsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface ReportPostsMapper extends BaseMapper<ReportPosts> {

    @Select(
    "<script>select t0.* from report_posts t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='pId!=null and pId!=&apos;&apos; '> and t0.p_id=#{pId}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<ReportPosts> pageAll(ReportPosts queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from report_posts t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='pId!=null and pId!=&apos;&apos; '> and t0.p_id=#{pId}</when> " +
     " </script>")
    int countAll(ReportPosts queryParamDTO);

}
