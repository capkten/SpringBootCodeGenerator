package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description news_leagueMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface NewsLeagueMapper {

    @Select("select * from news_league where news_league_id=#{id}")
    public NewsLeague getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="newsLeagueId")
    @Insert("insert into news_league" +
        " (n_id)" +
        " values(nId)")
    public Integer insert(NewsLeague newsLeague);

    @Delete(value = "delete from news_league where news_league_id=#{newsLeagueId}")
    boolean delete(Integer id);

    @Update(value = "update news_league set "
        +" n_id=#{nId}"
        +" where news_league_id=#{newsLeagueId} ")
    boolean update(NewsLeague newsLeague);


    @Results(value = {
        @Result(property = "nId", column = "n_id")
    })
    @Select(value = "select * from news_league where news_league_id=#{queryParam}")
    NewsLeague selectOne(String queryParam);

    @Results(value = {
        @Result(property = "nId", column = "n_id")
    })
    @Select(value = "select * from news_league where "
        +" n_id=#{nId}"
    )
    List<NewsLeague> selectList(NewsLeague newsLeague);

}