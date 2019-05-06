package com.bth.lht.service.impl;

import com.bth.lht.dao.teamDao.TeamMissionDaoMapper;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.service.TeamMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeamMissionServiceImpl implements TeamMissionService {

    @Autowired
    private TeamMissionDaoMapper teamMissionDaoMapper;


    @Override
    public List<MissionsEO> findByMissionTeamEOS(String teamId) {
        return teamMissionDaoMapper.findByMissionTeamEOS(teamId);
    }
}
