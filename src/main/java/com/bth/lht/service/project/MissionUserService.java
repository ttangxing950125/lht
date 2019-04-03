package com.bth.lht.service.project;

import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.respose.mission.MissionLeaderVO;

import java.util.List;

public interface MissionUserService {
    List<MissionUserEO> findMissionUserEOSByUserEO(UserEO userEO);
    MissionUserEO findMissionUserEOByUserEOAndMissionsEO(UserEO userEO, MissionsEO missionsEO);

    MissionUserEO save(MissionUserEO missionUserEO);

    void update(MissionUserEO missionUserEO);


    List<MissionLeaderVO> findMissionLeaderVO(String leaderOpenid);

    List<MissionUserEO> findMissionUserEOSByMissionEO(MissionsEO missionsEO);
}
