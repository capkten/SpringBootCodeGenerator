package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description news_teamMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface NewsTeamMapper {

    @Select("select * from news_team where news_team_id=#{id}")
    public NewsTeam getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="newsTeamId")
    @Insert("insert into news_team" +
        " (n_id)" +
        " values(nId)")
    public Integer insert(NewsTeam newsTeam);

    @Delete(value = "delete from news_team where news_team_id=#{newsTeamId}")
    boolean delete(Integer id);

    @Update(value = "update news_team set "
        +" n_id=#{nId}"
        +" where news_team_id=#{newsTeamId} ")
    boolean update(NewsTeam newsTeam);


    @Results(value = {
        @Result(property = "nId", column = "n_id")
    })
    @Select(value = "select * from news_team where news_team_id=#{queryParam}")
    NewsTeam selectOne(String queryParam);

    @Results(value = {
        @Result(property = "nId", column = "n_id")
    })
    @Select(value = "select * from news_team where "
        +" n_id=#{nId}"
    )
    List<NewsTeam> selectList(NewsTeam newsTeam);

}