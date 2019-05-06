package com.bth.lht.service;

import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.respose.team.TeamVO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamMissionService {
    List<MissionsEO> findByMissionTeamEOS( String teamId);

}
