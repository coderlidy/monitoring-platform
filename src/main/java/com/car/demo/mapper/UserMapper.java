package com.car.demo.mapper;

import com.car.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();
    @Select("select * from user where username = #{username} ")
    User findByUsername(Long username);
    @Select("select * from user where id = id")
    User findById(@Param("id")int idTest);//形参名和上面不一样要用@Param
    @Insert("insert into user (username,password,grade,name,age) values (#{username},#{password},#{grade},#{name},#{age})")
    void insert(User user);//形参是类不用加@Param
}
