package com.hzy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hzy.entity.Admin;
import java.util.List;
/**
 * @description adminMapper
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select(
    "<script>select t0.* from admin t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
    //add here if need page limit
    //" limit ${page},${limit} " +
    " </script>")
    List<Admin> pageAll(Admin queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from admin t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=#{id}</when> " +
     " </script>")
    int countAll(Admin queryParamDTO);

}
