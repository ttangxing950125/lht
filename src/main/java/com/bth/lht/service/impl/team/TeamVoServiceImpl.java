package com.bth.lht.service.impl.team;

import com.bth.lht.dao.team.TeamVo;
import com.bth.lht.respose.team.TeamVO;
import com.bth.lht.service.TeamVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamVoServiceImpl implements TeamVoService {
    @Autowired
    private TeamVo teamVo;

    @Override
    public TeamVO findByTeamId(String id) {
        return teamVo.findByTeamId(id);
    }

    @Override
    public List<TeamVO> findHotTeams() {
        return teamVo.findHotTeams();
    }
}
