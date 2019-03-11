package com.bth.lht.service.user;

import com.bth.lht.entity.user.UserInfoEO;

/**
 * 用户信息接口类
 */
public interface UserInfoService {
    UserInfoEO save(UserInfoEO userInfoEO);
    UserInfoEO findById(String id);
}
