package com.bth.lht.dao.team;

import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.user.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: lht
 * @description: 团队数据层
 * @author: Antony
 * @create: 2019-04-03 11:43
 **/

public interface TeamRepository extends JpaRepository<TeamEO,String> {
    //查找用户所拥有的团队
    List<TeamEO> findTeamEOSByUserEO(UserEO leader);

    List<TeamEO> findTeamEOSByLevel(String level);

    List<TeamEO> findAllByUserEO(UserEO userEO);
}
