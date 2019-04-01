package com.bth.lht.dao.project;

import com.bth.lht.entity.project.MissionCommentEO;
import com.bth.lht.entity.project.MissionsEO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionCommentRepository extends JpaRepository<MissionCommentEO,String> {
        //查找任务的所有评论
        List<MissionCommentEO> findMissionCommentEOSByMissionsEO(MissionsEO missionsEO);
}
