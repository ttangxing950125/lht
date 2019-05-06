package com.bth.lht.service.team;

import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.user.UserEO;

import java.util.List;

/**
 * @program: lht
 * @description: 团队业务层
 * @author: Antony
 * @create: 2019-04-03 11:45
 **/
public interface TeamService {
    List<TeamEO> findByLeader(UserEO leader);
    TeamEO addTeam(TeamEO teamEO);


    TeamEO getById(String id);

    List<TeamEO> list();

    List<TeamEO> getByLevel(String level);

    List<TeamEO> findAllByUserEO(UserEO userEO);

}
