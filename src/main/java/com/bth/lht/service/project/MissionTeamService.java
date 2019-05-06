package com.bth.lht.service.project;

import com.bth.lht.entity.project.MissionTeamEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.team.TeamEO;

import java.util.List;

public interface MissionTeamService {
    MissionTeamEO save(MissionTeamEO missionTeamEO);
    //获得所有数据
    List<MissionTeamEO> list();
    //获得用户所有的
    List<MissionTeamEO> getMissionTeamEO(TeamEO teamEO);
    MissionTeamEO findByTeamEOAndMissionsEO(TeamEO teamEO, MissionsEO missionsEO);
    List<MissionTeamEO> findAllByMissionsEO(MissionsEO missionsEO);
}
