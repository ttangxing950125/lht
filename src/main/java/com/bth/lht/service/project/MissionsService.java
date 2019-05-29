package com.bth.lht.service.project;

import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.mission.MissionRequest;

import java.util.List;

/**
 * @program: lht
 * @description: 任务服务层
 * @author: Antony
 * @create: 2019-03-19 10:06
 **/
public interface MissionsService {
    List<MissionsEO> findAllByOrderByCreateDateDesc();
    public List<MissionsEO> list();
    MissionsEO save(MissionsEO missionsEO);
    MissionsEO get(String id);
    List<MissionsEO> findByLeader(UserEO userEO);

}
