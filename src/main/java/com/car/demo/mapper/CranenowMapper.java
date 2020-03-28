package com.car.demo.mapper;

import com.car.demo.model.Cranenow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CranenowMapper {
    @Select("SELECT * FROM cranenow  WHERE carNumber = (SELECT carNumber FROM crane WHERE username = #{username}) ORDER BY gmtCreate DESC LIMIT 1")
    Cranenow findByUsername(Long username);
}
