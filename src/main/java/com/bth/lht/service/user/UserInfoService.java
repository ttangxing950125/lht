package com.bth.lht.service.user;

import com.bth.lht.entity.user.UserInfoEO;
import com.bth.lht.respose.wxUser.UserInfoVO;

/**
 * 用户信息接口类
 */
public interface UserInfoService {
    UserInfoEO save(UserInfoEO userInfoEO);
    UserInfoEO findById(String id);

    UserInfoVO findByTeamAndUserId(String userId, String teamId);
}
