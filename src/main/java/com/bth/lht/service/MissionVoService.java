package com.bth.lht.service;

import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.respose.mission.MissionVO;

import java.util.List;

public interface MissionVoService {
    public List<MissionVO> findAllByTeamId(String id);
    List<MissionsEO> findAllByOrderByCreateDateDesc();

}
