package com.car.demo.mapper;

import com.car.demo.model.Cranenow;
import com.car.demo.model.Position;
import com.car.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CranenowMapper {
    @Select("SELECT * FROM cranenow  WHERE carNumber = (SELECT carNumber FROM crane WHERE username = #{username}) ORDER BY gmtCreate DESC LIMIT 1")
    Cranenow findByUsername(Long username);
    @Select("SELECT positions FROM (SELECT * FROM cranenow ORDER BY gmtCreate DESC) AS temp GROUP BY  carNumber")
    List<String> findPositions();
    @Insert("insert into cranenow (carNumber,positions,nowWeight,gmtCreate,gmtModified) values (#{carNumber},#{positions},#{nowWeight},#{gmtCreate},#{gmtModified})")
    Integer insert(Cranenow cranenow);

}
