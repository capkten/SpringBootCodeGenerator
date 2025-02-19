package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description likes_postsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface LikesPostsMapper {

    @Select("select * from likes_posts where likes_posts_id=#{id}")
    public LikesPosts getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="likesPostsId")
    @Insert("insert into likes_posts" +
        " (id)" +
        " values(id)")
    public Integer insert(LikesPosts likesPosts);

    @Delete(value = "delete from likes_posts where likes_posts_id=#{likesPostsId}")
    boolean delete(Integer id);

    @Update(value = "update likes_posts set "
        
        +" where likes_posts_id=#{likesPostsId} ")
    boolean update(LikesPosts likesPosts);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from likes_posts where likes_posts_id=#{queryParam}")
    LikesPosts selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from likes_posts where "
        +" id=#{id}"
    )
    List<LikesPosts> selectList(LikesPosts likesPosts);

}