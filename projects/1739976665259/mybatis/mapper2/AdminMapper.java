package com.hzy.mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @description adminMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface AdminMapper {

    @Select("select * from admin where admin_id=#{id}")
    public Admin getById(Integer id);

    @Options(useGeneratedKeys=true,keyProperty="adminId")
    @Insert("insert into admin" +
        " (id)" +
        " values(id)")
    public Integer insert(Admin admin);

    @Delete(value = "delete from admin where admin_id=#{adminId}")
    boolean delete(Integer id);

    @Update(value = "update admin set "
        
        +" where admin_id=#{adminId} ")
    boolean update(Admin admin);


    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from admin where admin_id=#{queryParam}")
    Admin selectOne(String queryParam);

    @Results(value = {
        @Result(property = "id", column = "id")
    })
    @Select(value = "select * from admin where "
        +" id=#{id}"
    )
    List<Admin> selectList(Admin admin);

}