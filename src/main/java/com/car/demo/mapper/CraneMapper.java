package com.car.demo.mapper;

import com.car.demo.model.Crane;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CraneMapper {
    @Select("select * from crane")
    List<Crane> findAll();
}
