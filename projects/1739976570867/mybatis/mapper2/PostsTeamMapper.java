package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description posts_teamMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface PostsTeamMapper {

    @Select("select * from posts_team where posts_team_id=#{id}")
    public PostsTeam getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="postsTeamId")
    @Insert("insert into posts_team" +
        " (id)" +
        " values(id)")
    public Integer insert(PostsTeam postsTeam);

    @Delete(value = "delete from posts_team where posts_team_id=#{postsTeamId}")
    boolean delete(Integer id);

    @Update(value = "update posts_team set "
        
        +" where posts_team_id=#{postsTeamId} ")
    boolean update(PostsTeam postsTeam);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from posts_team where posts_team_id=#{queryParam}")
    PostsTeam selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from posts_team where "
        +" id=#{id}"
    )
    List<PostsTeam> selectList(PostsTeam postsTeam);

}