package com.bth.lht.service.impl.user;

import com.bth.lht.dao.user.UserRepository;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UserRepository userRepository;
    @Override
    public UserEO save(UserEO userEO) {

        return userRepository.save(userEO);
    }

    @Override
    public UserEO findById(String id) {
        return userRepository.findUserEOById(id);
    }

}
