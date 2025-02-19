package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.ReportCommentsPosts;
import java.util.List;
/**
 * @description report_comments_postsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface ReportCommentsPostsMapper extends BaseMapper<ReportCommentsPosts> {

    @Select(
    "<script>select t0.* from report_comments_posts t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='cpId!=null and cpId!=&apos;&apos; '> and t0.cp_id=#{cpId}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<ReportCommentsPosts> pageAll(ReportCommentsPosts queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from report_comments_posts t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='cpId!=null and cpId!=&apos;&apos; '> and t0.cp_id=#{cpId}</when> " +
     " </script>")
    int countAll(ReportCommentsPosts queryParamDTO);

}
