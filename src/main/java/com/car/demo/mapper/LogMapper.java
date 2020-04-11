package com.car.demo.mapper;

import com.car.demo.model.Log;
import com.car.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LogMapper {
    @Select("select * from `log` ORDER BY gmtCreate DESC limit #{index},#{size}")
    List<Log> findAll(int index, int size);
    @Select("select count(*) from `log`")
    int getLogCount();
    @Insert("insert into `log` (`code`,operator,`describe`,object,ip,gmtCreate) values (#{code},#{operator},#{describe},#{object},#{ip},#{gmtCreate})")
    Integer insert(Log log);
}
