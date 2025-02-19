package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description collect_newsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface CollectNewsMapper {

    @Select("select * from collect_news where collect_news_id=#{id}")
    public CollectNews getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="collectNewsId")
    @Insert("insert into collect_news" +
        " (id)" +
        " values(id)")
    public Integer insert(CollectNews collectNews);

    @Delete(value = "delete from collect_news where collect_news_id=#{collectNewsId}")
    boolean delete(Integer id);

    @Update(value = "update collect_news set "
        
        +" where collect_news_id=#{collectNewsId} ")
    boolean update(CollectNews collectNews);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from collect_news where collect_news_id=#{queryParam}")
    CollectNews selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from collect_news where "
        +" id=#{id}"
    )
    List<CollectNews> selectList(CollectNews collectNews);

}