package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description likes_newsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface LikesNewsMapper {

    @Select("select * from likes_news where likes_news_id=#{id}")
    public LikesNews getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="likesNewsId")
    @Insert("insert into likes_news" +
        " (id)" +
        " values(id)")
    public Integer insert(LikesNews likesNews);

    @Delete(value = "delete from likes_news where likes_news_id=#{likesNewsId}")
    boolean delete(Integer id);

    @Update(value = "update likes_news set "
        
        +" where likes_news_id=#{likesNewsId} ")
    boolean update(LikesNews likesNews);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from likes_news where likes_news_id=#{queryParam}")
    LikesNews selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from likes_news where "
        +" id=#{id}"
    )
    List<LikesNews> selectList(LikesNews likesNews);

}