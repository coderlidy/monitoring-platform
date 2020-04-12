package com.car.demo.mapper;

import com.car.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from (SELECT * FROM USER WHERE CONCAT(IFNULL(`username`,''),IFNULL(`name`,''),IFNULL(`age`,'')) LIKE '%${search}%') AS temp ORDER BY gmtCreate DESC limit #{index},#{size}")
    List<User> findSearch(String search,int index,int size);
    @Select("select * from user ORDER BY gmtCreate DESC limit #{index},#{size}")
    List<User> findAll(int index,int size);
    @Select("SELECT count(*) FROM USER WHERE CONCAT(IFNULL(`username`,''),IFNULL(`name`,''),IFNULL(`age`,'')) LIKE '%${search}%'")
    int getSearchCount(String search);
    @Select("select count(*) from user")
    int getUserCount();
    @Select("select name from user where username = #{username} ")
    String findNameByUsername(Long username);
    @Select("select password from user where username = #{username} ")
    String findPasswordByUsername(Long username);
    @Select("select * from user where username = #{username} ")
    User findByUsername(Long username);
    @Select("select username from user where id = #{id}")
    Long findUsernameById(@Param("id")Long idTest);//形参名和上面不一样要用@Param
    @Insert("insert into user (username,password,grade,name,age,gmtCreate,gmtModified) values (#{username},#{password},#{grade},#{name},#{age},#{gmtCreate},#{gmtModified})")
    Integer insert(User user);//形参是类不用加@Param
    @Update("update user set username=#{username},password=#{password},grade=#{grade},name=#{name},age=#{age},gmtModified=#{gmtModified} where id = #{id}")
    Integer updateById(User user);
    @Delete("delete from user where id = #{id}")
    Integer deleteById(Long id);

}
