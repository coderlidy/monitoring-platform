package com.car.demo.dto;

import com.car.demo.model.Crane;
import com.car.demo.model.User;

public class UserCraneDTO {
    private UserDTO userDTO;
    private CraneDTO craneDTO;
    public UserCraneDTO(UserDTO userDTO,CraneDTO craneDTO){
        this.userDTO=userDTO;
        this.craneDTO=craneDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public CraneDTO getCraneDTO() {
        return craneDTO;
    }

    public void setCraneDTO(CraneDTO craneDTO) {
        this.craneDTO = craneDTO;
    }
}
