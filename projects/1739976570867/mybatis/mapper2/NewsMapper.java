package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description newsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface NewsMapper {

    @Select("select * from news where news_id=#{id}")
    public News getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="newsId")
    @Insert("insert into news" +
        " (id)" +
        " values(id)")
    public Integer insert(News news);

    @Delete(value = "delete from news where news_id=#{newsId}")
    boolean delete(Integer id);

    @Update(value = "update news set "
        
        +" where news_id=#{newsId} ")
    boolean update(News news);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from news where news_id=#{queryParam}")
    News selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from news where "
        +" id=#{id}"
    )
    List<News> selectList(News news);

}