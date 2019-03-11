package com.bth.lht.dao.user;

import com.bth.lht.entity.user.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: lht
 * @description: dao-用户
 * @author: Antony
 * @create: 2018-12-21 18:13
 **/
public interface UserRepository extends JpaRepository<UserEO,String> {
    UserEO findUserEOById(String id);
}
