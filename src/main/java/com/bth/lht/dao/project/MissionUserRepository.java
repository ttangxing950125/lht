package com.bth.lht.dao.project;

import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: lht
 * @description: 用户接收任务数据层
 * @author: Antony
 * @create: 2019-04-01 21:40
 **/
public interface MissionUserRepository extends JpaRepository<MissionUserEO,String> {
    List<MissionUserEO> findMissionUserEOSByUserEO(UserEO userEO);

    MissionUserEO findMissionUserEOByUserEOAndMissionsEO(UserEO userEO,MissionsEO missionsEO);

    List<MissionUserEO> findMissionUserEOSByMissionsEO(MissionsEO m);

//    List<MissionUserEO> findMissionUserEOSByMissionEO(MissionsEO missionsEO);
}
