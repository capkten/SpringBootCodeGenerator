package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description leagueMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface LeagueMapper {

    @Select("select * from league where league_id=#{id}")
    public League getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="leagueId")
    @Insert("insert into league" +
        " (id)" +
        " values(id)")
    public Integer insert(League league);

    @Delete(value = "delete from league where league_id=#{leagueId}")
    boolean delete(Integer id);

    @Update(value = "update league set "
        
        +" where league_id=#{leagueId} ")
    boolean update(League league);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from league where league_id=#{queryParam}")
    League selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from league where "
        +" id=#{id}"
    )
    List<League> selectList(League league);

}