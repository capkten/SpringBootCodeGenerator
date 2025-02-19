package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description collect_postsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface CollectPostsMapper {

    @Select("select * from collect_posts where collect_posts_id=#{id}")
    public CollectPosts getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="collectPostsId")
    @Insert("insert into collect_posts" +
        " (id)" +
        " values(id)")
    public Integer insert(CollectPosts collectPosts);

    @Delete(value = "delete from collect_posts where collect_posts_id=#{collectPostsId}")
    boolean delete(Integer id);

    @Update(value = "update collect_posts set "
        
        +" where collect_posts_id=#{collectPostsId} ")
    boolean update(CollectPosts collectPosts);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from collect_posts where collect_posts_id=#{queryParam}")
    CollectPosts selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from collect_posts where "
        +" id=#{id}"
    )
    List<CollectPosts> selectList(CollectPosts collectPosts);

}