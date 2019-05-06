package com.bth.lht.service;

import com.bth.lht.respose.team.TeamVO;

import java.util.List;

public interface TeamVoService {

    TeamVO findByTeamId(String id);
    List<TeamVO> findHotTeams();

}
