package com.car.demo.wx.service;

import com.car.demo.dto.CraneDTO;
import com.car.demo.dto.CranenowDTO;
import com.car.demo.dto.UserCraneDTO;
import com.car.demo.dto.UserDTO;
import com.car.demo.mapper.CraneMapper;
import com.car.demo.mapper.CranenowMapper;
import com.car.demo.mapper.UserMapper;
import com.car.demo.model.Crane;
import com.car.demo.model.User;
import com.car.demo.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyCraneInfo {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CraneMapper craneMapper;
    @Autowired
    private CranenowMapper cranenowMapper;
    @Autowired
    private ConvertService convertService;
    public UserCraneDTO findUserCraneByUsername(Long username){
        UserDTO userDTO=convertService.userToUserDTO(userMapper.findByUsername(username));
        CraneDTO craneDTO=convertService.craneToCraneDTO(craneMapper.findByUsername(username));
        return new UserCraneDTO(userDTO,craneDTO);
    }
    public CranenowDTO findCranenowByUsername(Long username){
        return convertService.cranenowToCranenowDTO(cranenowMapper.findByUsername(username));
    }
}
