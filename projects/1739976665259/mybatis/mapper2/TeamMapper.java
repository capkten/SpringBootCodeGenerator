package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description teamMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface TeamMapper {

    @Select("select * from team where team_id=#{id}")
    public Team getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="teamId")
    @Insert("insert into team" +
        " (id)" +
        " values(id)")
    public Integer insert(Team team);

    @Delete(value = "delete from team where team_id=#{teamId}")
    boolean delete(Integer id);

    @Update(value = "update team set "
        
        +" where team_id=#{teamId} ")
    boolean update(Team team);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from team where team_id=#{queryParam}")
    Team selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from team where "
        +" id=#{id}"
    )
    List<Team> selectList(Team team);

}