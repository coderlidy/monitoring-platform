package com.car.demo.mapper;

import com.car.demo.model.Crane;
import org.apache.ibatis.annotations.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Mapper
public interface CraneMapper {
    @Select("select * from crane")
    List<Crane> findAll();
    @Select("select * from crane where username = #{username}")
    Crane findByUsername(Long username);
    @Select("select maxWeightCount from crane where username = #{username}")
    Double findMaxWeightCountByUsername(Long username);
    @Insert("insert into crane (carNumber,carTypeNumber,username,maxLiftWeight,nowWeightCount,maxWeightCount,birthday,useDay,gmtCreate,gmtModified) values (#{carNumber},#{carTypeNumber},#{username},#{maxLiftWeight},#{nowWeightCount},#{maxWeightCount},#{birthday},#{useDay},#{gmtCreate},#{gmtModified})")
    Integer insert(Crane crane);//形参是类不用加@Param
    @Update("update crane set carNumber=#{carNumber},carTypeNumber=#{carTypeNumber},username=#{username},maxLiftWeight=#{maxLiftWeight},nowWeightCount=#{nowWeightCount},maxWeightCount=#{maxWeightCount},birthday=#{birthday},useDay=#{useDay},gmtModified=#{gmtModified} where id = #{id}")
    Integer updateById(Crane crane);
    @Delete("delete from crane where id = #{id}")
    Integer deleteById(Long id);
}
