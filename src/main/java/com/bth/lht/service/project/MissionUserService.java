package com.bth.lht.service.project;

import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.user.UserEO;

import java.util.List;

public interface MissionUserService {
    List<MissionUserEO> findMissionUserEOSByUserEO(UserEO userEO);

    MissionUserEO save(MissionUserEO missionUserEO);
}
