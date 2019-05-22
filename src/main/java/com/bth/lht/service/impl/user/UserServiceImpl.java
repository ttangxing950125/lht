package com.bth.lht.service.impl.user;

import com.bth.lht.dao.user.UserRepository;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.user.LoginRequest;
import com.bth.lht.respose.wxUser.WxUserKeyVO;
import com.bth.lht.service.WxAppletService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @program: lht
 * @description: impl-用户实现
 * @author: Antony
 * @create: 2018-12-21 18:15
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private WxAppletService wxAppletService;
    @Autowired
    private UserRepository userRepository;
    @Override
    public String save(LoginRequest loginRequest) {
        WxUserKeyVO sessionKeyOrOpenid = wxAppletService.getSessionKeyOrOpenid(loginRequest.getCode());
        System.out.println("查到openid"+sessionKeyOrOpenid.toString());
        UserEO userEO = userRepository.findUserEOByWxOpenid(sessionKeyOrOpenid.getOpenid());
        if ( userEO != null){
            userEO.setWxOpenid(sessionKeyOrOpenid.getOpenid());
            userEO.setWxNickName(loginRequest.getUserRequest().getNickName());
            userEO.setAvatarUrl(loginRequest.getUserRequest().getAvatarUrl());
        }else {
            //实例化
            userEO = new UserEO();
            userEO.setWxOpenid(sessionKeyOrOpenid.getOpenid());
            userEO.setWxNickName(loginRequest.getUserRequest().getNickName());
            userEO.setAvatarUrl(loginRequest.getUserRequest().getAvatarUrl());
            userEO.setIntegral(new BigDecimal("1000"));
        }
        userRepository.save(userEO);
        return TokenUtil.createToken(sessionKeyOrOpenid.getOpenid());
    }

    @Override
    public UserEO save(UserEO userEO) {
        return userRepository.save(userEO);
    }

    @Override
    public UserEO findByOpenid(String openid) {
        System.out.println(openid);
        return userRepository.findUserEOByWxOpenid(openid);
    }

    @Override
    public UserEO findById(String id) {
        return userRepository.getOne(id);
    }

}
