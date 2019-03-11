package com.bth.lht.dao.user;

import com.bth.lht.entity.user.UserInfoEO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: lht
 * @description: dao层-用户信息
 * @author: Antony
 * @create: 2018-12-21 14:06
 **/

public interface UserInfoRepository extends JpaRepository<UserInfoEO,String> {
    UserInfoEO findUserInfoEOById(String id);

}
