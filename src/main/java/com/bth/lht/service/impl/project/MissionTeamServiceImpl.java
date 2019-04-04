package com.bth.lht.service.impl.project;

import com.bth.lht.dao.project.MissionTeamRepository;
import com.bth.lht.entity.project.MissionTeamEO;
import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.service.project.MissionTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: lht
 * @description: 团队接收任务实现 层
 * @author: Antony
 * @create: 2019-04-04 00:42
 **/
@Service
public class MissionTeamServiceImpl implements MissionTeamService {
    @Autowired
    private MissionTeamRepository missionTeamRepository;
    @Override
    public MissionTeamEO save(MissionTeamEO missionTeamEO) {
        return missionTeamRepository.save(missionTeamEO);
    }

    @Override
    public List<MissionTeamEO> list() {
        return missionTeamRepository.findAll();
    }

    @Override
    public List<MissionTeamEO> getMissionTeamEO(TeamEO teamEO) {
        return missionTeamRepository.findMissionTeamEOByTeamEO(teamEO);
    }
}
