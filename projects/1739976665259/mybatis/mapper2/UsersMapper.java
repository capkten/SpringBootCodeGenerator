package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description usersMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface UsersMapper {

    @Select("select * from users where users_id=#{id}")
    public Users getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="usersId")
    @Insert("insert into users" +
        " (id)" +
        " values(id)")
    public Integer insert(Users users);

    @Delete(value = "delete from users where users_id=#{usersId}")
    boolean delete(Integer id);

    @Update(value = "update users set "
        
        +" where users_id=#{usersId} ")
    boolean update(Users users);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from users where users_id=#{queryParam}")
    Users selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from users where "
        +" id=#{id}"
    )
    List<Users> selectList(Users users);

}