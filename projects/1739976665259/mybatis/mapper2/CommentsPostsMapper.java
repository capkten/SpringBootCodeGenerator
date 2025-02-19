package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description comments_postsMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface CommentsPostsMapper {

    @Select("select * from comments_posts where comments_posts_id=#{id}")
    public CommentsPosts getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="commentsPostsId")
    @Insert("insert into comments_posts" +
        " (id)" +
        " values(id)")
    public Integer insert(CommentsPosts commentsPosts);

    @Delete(value = "delete from comments_posts where comments_posts_id=#{commentsPostsId}")
    boolean delete(Integer id);

    @Update(value = "update comments_posts set "
        
        +" where comments_posts_id=#{commentsPostsId} ")
    boolean update(CommentsPosts commentsPosts);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from comments_posts where comments_posts_id=#{queryParam}")
    CommentsPosts selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from comments_posts where "
        +" id=#{id}"
    )
    List<CommentsPosts> selectList(CommentsPosts commentsPosts);

}