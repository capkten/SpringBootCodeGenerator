package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description report_comments_newsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface ReportCommentsNewsMapper {

    @Select("select * from report_comments_news where report_comments_news_id=#{id}")
    public ReportCommentsNews getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="reportCommentsNewsId")
    @Insert("insert into report_comments_news" +
        " (cn_id)" +
        " values(cnId)")
    public Integer insert(ReportCommentsNews reportCommentsNews);

    @Delete(value = "delete from report_comments_news where report_comments_news_id=#{reportCommentsNewsId}")
    boolean delete(Integer id);

    @Update(value = "update report_comments_news set "
        +" cn_id=#{cnId}"
        +" where report_comments_news_id=#{reportCommentsNewsId} ")
    boolean update(ReportCommentsNews reportCommentsNews);


    @Results(value = {
        @Result(property = "cnId", column = "cn_id")
    })
    @Select(value = "select * from report_comments_news where report_comments_news_id=#{queryParam}")
    ReportCommentsNews selectOne(String queryParam);

    @Results(value = {
        @Result(property = "cnId", column = "cn_id")
    })
    @Select(value = "select * from report_comments_news where "
        +" cn_id=#{cnId}"
    )
    List<ReportCommentsNews> selectList(ReportCommentsNews reportCommentsNews);

}