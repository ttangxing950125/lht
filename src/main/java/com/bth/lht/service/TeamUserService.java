package com.bth.lht.service;

import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.team.TeamUserEO;
import com.bth.lht.entity.user.UserEO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeamUserService {
    //查找用户自己所在的团队
    List<TeamUserEO> getByUserEO(UserEO userEO);

    TeamUserEO add(TeamUserEO teamUserEO);
    //查找用户是否以及加入该团队
    TeamUserEO findTeamUserEOByUserEOAndTeamEO(UserEO userEO , TeamEO teamEO);

    List<TeamUserEO> getByUserEOAndStatus(UserEO userEO, String status);

    //查找一个团队的人数
    List<TeamUserEO>  countTeamUserEOSByTeamEO(TeamEO teamEO);
    //通过团队查找
    List<TeamUserEO>  findByTeamEO(TeamEO teamEO);

    TeamUserEO update(TeamUserEO teamUserEO);

}
