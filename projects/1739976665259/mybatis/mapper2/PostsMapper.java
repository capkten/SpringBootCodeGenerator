package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description postsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface PostsMapper {

    @Select("select * from posts where posts_id=#{id}")
    public Posts getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="postsId")
    @Insert("insert into posts" +
        " (id)" +
        " values(id)")
    public Integer insert(Posts posts);

    @Delete(value = "delete from posts where posts_id=#{postsId}")
    boolean delete(Integer id);

    @Update(value = "update posts set "
        
        +" where posts_id=#{postsId} ")
    boolean update(Posts posts);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from posts where posts_id=#{queryParam}")
    Posts selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from posts where "
        +" id=#{id}"
    )
    List<Posts> selectList(Posts posts);

}