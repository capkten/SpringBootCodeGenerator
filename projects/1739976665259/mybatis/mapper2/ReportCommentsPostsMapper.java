package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description report_comments_postsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface ReportCommentsPostsMapper {

    @Select("select * from report_comments_posts where report_comments_posts_id=#{id}")
    public ReportCommentsPosts getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="reportCommentsPostsId")
    @Insert("insert into report_comments_posts" +
        " (cp_id)" +
        " values(cpId)")
    public Integer insert(ReportCommentsPosts reportCommentsPosts);

    @Delete(value = "delete from report_comments_posts where report_comments_posts_id=#{reportCommentsPostsId}")
    boolean delete(Integer id);

    @Update(value = "update report_comments_posts set "
        +" cp_id=#{cpId}"
        +" where report_comments_posts_id=#{reportCommentsPostsId} ")
    boolean update(ReportCommentsPosts reportCommentsPosts);


    @Results(value = {
        @Result(property = "cpId", column = "cp_id")
    })
    @Select(value = "select * from report_comments_posts where report_comments_posts_id=#{queryParam}")
    ReportCommentsPosts selectOne(String queryParam);

    @Results(value = {
        @Result(property = "cpId", column = "cp_id")
    })
    @Select(value = "select * from report_comments_posts where "
        +" cp_id=#{cpId}"
    )
    List<ReportCommentsPosts> selectList(ReportCommentsPosts reportCommentsPosts);

}