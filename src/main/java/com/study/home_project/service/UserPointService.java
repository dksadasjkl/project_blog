package com.study.home_project.service;

import com.study.home_project.dto.request.UserUseOrSavePointRequestDto;
import com.study.home_project.entity.User;
import com.study.home_project.repository.PointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPointService {

    @Autowired
    private PointMapper pointMapper;

    public int useOrSavePointByNumber(UserUseOrSavePointRequestDto UserUseOrSavePointReqDto) {
        User user = pointMapper.findUser(UserUseOrSavePointReqDto.getPhoneNumber());
        if(user != null) {
            return pointMapper.useOrSavePoint(
                    user.getUserId(),
                    UserUseOrSavePointReqDto.getPoint());
        }
        return 0;
    }

    public int getPointByPhoneNumber(String phoneNumber) {
        Integer success = pointMapper.getPoint((phoneNumber));
        if(success == null){
            return 0;
        }
        return success;
    }


}
