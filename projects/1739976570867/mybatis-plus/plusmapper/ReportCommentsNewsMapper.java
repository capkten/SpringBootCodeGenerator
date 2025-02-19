package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.ReportCommentsNews;
import java.util.List;
/**
 * @description report_comments_newsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface ReportCommentsNewsMapper extends BaseMapper<ReportCommentsNews> {

    @Select(
    "<script>select t0.* from report_comments_news t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='cnId!=null and cnId!=&apos;&apos; '> and t0.cn_id=#{cnId}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<ReportCommentsNews> pageAll(ReportCommentsNews queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from report_comments_news t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='cnId!=null and cnId!=&apos;&apos; '> and t0.cn_id=#{cnId}</when> " +
     " </script>")
    int countAll(ReportCommentsNews queryParamDTO);

}
