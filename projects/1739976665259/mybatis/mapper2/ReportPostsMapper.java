package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description report_postsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface ReportPostsMapper {

    @Select("select * from report_posts where report_posts_id=#{id}")
    public ReportPosts getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="reportPostsId")
    @Insert("insert into report_posts" +
        " (p_id)" +
        " values(pId)")
    public Integer insert(ReportPosts reportPosts);

    @Delete(value = "delete from report_posts where report_posts_id=#{reportPostsId}")
    boolean delete(Integer id);

    @Update(value = "update report_posts set "
        +" p_id=#{pId}"
        +" where report_posts_id=#{reportPostsId} ")
    boolean update(ReportPosts reportPosts);


    @Results(value = {
        @Result(property = "pId", column = "p_id")
    })
    @Select(value = "select * from report_posts where report_posts_id=#{queryParam}")
    ReportPosts selectOne(String queryParam);

    @Results(value = {
        @Result(property = "pId", column = "p_id")
    })
    @Select(value = "select * from report_posts where "
        +" p_id=#{pId}"
    )
    List<ReportPosts> selectList(ReportPosts reportPosts);

}