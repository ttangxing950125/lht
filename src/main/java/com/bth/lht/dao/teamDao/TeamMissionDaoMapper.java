package com.bth.lht.dao.teamDao;

import com.bth.lht.entity.project.MissionsEO;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TeamMissionDaoMapper extends JpaRepository<MissionsEO,String> {

    //查找当前这个团队的所有的任务
    List<MissionsEO> findByMissionTeamEOS(String id);

    List<MissionsEO> findAll();


}
