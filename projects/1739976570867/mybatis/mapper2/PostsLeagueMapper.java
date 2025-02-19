package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description posts_leagueMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface PostsLeagueMapper {

    @Select("select * from posts_league where posts_league_id=#{id}")
    public PostsLeague getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="postsLeagueId")
    @Insert("insert into posts_league" +
        " (id)" +
        " values(id)")
    public Integer insert(PostsLeague postsLeague);

    @Delete(value = "delete from posts_league where posts_league_id=#{postsLeagueId}")
    boolean delete(Integer id);

    @Update(value = "update posts_league set "
        
        +" where posts_league_id=#{postsLeagueId} ")
    boolean update(PostsLeague postsLeague);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from posts_league where posts_league_id=#{queryParam}")
    PostsLeague selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from posts_league where "
        +" id=#{id}"
    )
    List<PostsLeague> selectList(PostsLeague postsLeague);

}