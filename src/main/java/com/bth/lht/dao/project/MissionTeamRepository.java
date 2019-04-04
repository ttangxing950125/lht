package com.bth.lht.dao.project;

import com.bth.lht.entity.project.MissionTeamEO;
import com.bth.lht.entity.team.TeamEO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: lht
 * @description: 团队接收任务 数据层
 * @author: Antony
 * @create: 2019-04-04 00:24
 **/
public interface MissionTeamRepository extends JpaRepository<MissionTeamEO,String> {
    List<MissionTeamEO> findMissionTeamEOByTeamEO(TeamEO teamEO);
}
