package com.bth.lht.service.impl.team;

import com.bth.lht.dao.team.TeamRepository;
import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: lht
 * @description: 团队业务实现层
 * @author: Antony
 * @create: 2019-04-03 11:46
 **/
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<TeamEO> findByLeader(UserEO leader) {
        return teamRepository.findTeamEOSByUserEO(leader);
    }

    @Override
    public TeamEO addTeam(TeamEO teamEO) {
        return teamRepository.save(teamEO);
    }

    @Override
    public TeamEO getById(String id) {
        return teamRepository.getOne(id);
    }

    @Override
    public List<TeamEO> list() {
        return teamRepository.findAll();
    }

    @Override
    public List<TeamEO> getByLevel(String level) {
        return teamRepository.findTeamEOSByLevel(level);
    }

    @Override
    public List<TeamEO> findAllByUserEO(UserEO userEO) {
        return teamRepository.findAllByUserEO(userEO);
    }


}
