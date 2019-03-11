package com.bth.lht.service.user;

import com.bth.lht.entity.user.UserEO;

/**
 * @program: lht
 * @description: service-用户
 * @author: Antony
 * @create: 2018-12-21 18:14
 **/
public interface UserService {
    UserEO save(UserEO userEO);
    UserEO findById(String id);
}
