package com.bth.lht.service.impl.team;

import com.bth.lht.dao.team.TeamUserRepository;
import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.team.TeamUserEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.service.TeamUserService;
import com.bth.lht.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: lht
 * @description:
 * @author: Antony
 * @create: 2019-04-04 17:41
 **/
@Service
public class TeamUserServiceImpl implements TeamUserService {
    @Autowired
    private TeamUserRepository teamUserRepository;

    @Override
    public List<TeamUserEO> getByUserEO(UserEO userEO) {
        return teamUserRepository.findTeamUserEOByUserEO(userEO);
    }

    @Override
    public TeamUserEO add(TeamUserEO teamUserEO) {
//        return teamUserRepository.save(userEO);
        return teamUserRepository.save(teamUserEO);
    }

    @Override
    public TeamUserEO findTeamUserEOSByUserEOAndTeamEO(UserEO userEO, TeamEO teamEO) {
        return teamUserRepository.findTeamUserEOSByUserEOAndTeamEO(userEO,teamEO);
    }

    @Override
    public List<TeamUserEO> getByUserEOAndStatus(UserEO userEO, String status) {
        return teamUserRepository.findTeamUserEOSByUserEOAndStatus(userEO,status);
    }
}
