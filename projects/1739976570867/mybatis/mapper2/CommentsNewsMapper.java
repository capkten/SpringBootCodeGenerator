package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description comments_newsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface CommentsNewsMapper {

    @Select("select * from comments_news where comments_news_id=#{id}")
    public CommentsNews getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="commentsNewsId")
    @Insert("insert into comments_news" +
        " (id)" +
        " values(id)")
    public Integer insert(CommentsNews commentsNews);

    @Delete(value = "delete from comments_news where comments_news_id=#{commentsNewsId}")
    boolean delete(Integer id);

    @Update(value = "update comments_news set "
        
        +" where comments_news_id=#{commentsNewsId} ")
    boolean update(CommentsNews commentsNews);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from comments_news where comments_news_id=#{queryParam}")
    CommentsNews selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from comments_news where "
        +" id=#{id}"
    )
    List<CommentsNews> selectList(CommentsNews commentsNews);

}