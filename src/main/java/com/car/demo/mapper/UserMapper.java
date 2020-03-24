package com.car.demo.mapper;

import com.car.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();
    @Select("select name from user where username = #{username} ")
    String findNameByUsername(Long username);
    @Select("select password from user where username = #{username} ")
    String findPasswordByUsername(Long username);
    @Select("select * from user where username = #{username} ")
    User findByUsername(Long username);
    @Select("select * from user where id = #{id}")
    User findById(@Param("id")Long idTest);//形参名和上面不一样要用@Param
    @Insert("insert into user (username,password,grade,name,age,gmtCreate,gmtModified) values (#{username},#{password},#{grade},#{name},#{age},#{gmtCreate},#{gmtModified})")
    Integer insert(User user);//形参是类不用加@Param
    @Update("update user set username=#{username},password=#{password},grade=#{grade},name=#{name},age=#{age},gmtModified=#{gmtModified} where id = #{id}")
    Integer updateById(User user);
    @Delete("delete from user where id = #{id}")
    Integer deleteById(Long id);

}
