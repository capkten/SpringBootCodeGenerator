package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description news_editorMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface NewsEditorMapper {

    @Select("select * from news_editor where news_editor_id=#{id}")
    public NewsEditor getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="newsEditorId")
    @Insert("insert into news_editor" +
        " (id)" +
        " values(id)")
    public Integer insert(NewsEditor newsEditor);

    @Delete(value = "delete from news_editor where news_editor_id=#{newsEditorId}")
    boolean delete(Integer id);

    @Update(value = "update news_editor set "
        
        +" where news_editor_id=#{newsEditorId} ")
    boolean update(NewsEditor newsEditor);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from news_editor where news_editor_id=#{queryParam}")
    NewsEditor selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from news_editor where "
        +" id=#{id}"
    )
    List<NewsEditor> selectList(NewsEditor newsEditor);

}