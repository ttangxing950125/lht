package com.bth.lht.service.project;

import com.bth.lht.entity.project.MissionCommentEO;
import com.bth.lht.entity.project.MissionsEO;

import java.util.List;

public interface MissionCommentService {
    List<MissionCommentEO> findMissionCommentEOSByMissionsEO(MissionsEO missionsEO);

    MissionCommentEO save(MissionCommentEO commentEO);

    MissionCommentEO get(String id);

    void delete(String id);
}
