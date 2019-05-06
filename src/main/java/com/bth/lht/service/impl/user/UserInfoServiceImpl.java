package com.bth.lht.service.impl.user;

import com.bth.lht.dao.user.UserInfoDao;
import com.bth.lht.dao.user.UserInfoRepository;
import com.bth.lht.entity.user.UserInfoEO;
import com.bth.lht.respose.wxUser.UserInfoVO;
import com.bth.lht.service.user.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: lht
 * @description: service层-用户信息
 * @author: Antony
 * @create: 2018-12-21 14:10
 **/
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserInfoDao ufd;

    /**
     * 添加用户信息
     * @param userInfoEO
     * @return
     */
    public UserInfoEO save(UserInfoEO userInfoEO){
        return userInfoRepository.save(userInfoEO);
    }

    @Override
    public UserInfoEO findById(String id) {

        return userInfoRepository.findUserInfoEOById(id);
    }

    @Override
    public UserInfoVO findByTeamAndUserId(String userId, String teamId) {
        return ufd.findByTeamAndUserId(userId,teamId);
    }
}
