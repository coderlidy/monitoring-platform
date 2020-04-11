package com.car.demo.service;

import com.car.demo.dto.LogDTO;
import com.car.demo.mapper.LogMapper;
import com.car.demo.model.Crane;
import com.car.demo.model.Log;
import com.car.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;
    public List<LogDTO> findAll(int index,int size){
        List<Log> logList=logMapper.findAll(index,size);
        List<LogDTO> logDTOList=new ArrayList<>();
        for (Log temp:logList){
            LogDTO logDTO=new LogDTO();
            logDTO.setId(temp.getId());
            logDTO.setOperator(temp.getOperator());
            logDTO.setCode(temp.getCode());
            logDTO.setDescribe(temp.getDescribe());
            logDTO.setIp(temp.getIp());
            logDTO.setObject(temp.getObject());
            logDTO.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(temp.getGmtCreate()));
            logDTOList.add(logDTO);
        }
        return logDTOList;
    }
}
