package com.bth.lht.dao.project;

import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: lht
 * @description: 任务数据层
 * @author: Antony
 * @create: 2019-03-19 10:04
 **/
public interface MissionsRepository extends JpaRepository<MissionsEO,String> {
    List<MissionsEO> findMissionsEOByLeaderEO(UserEO userEO);


}
