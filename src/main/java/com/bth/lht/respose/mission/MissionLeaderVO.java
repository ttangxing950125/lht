package com.bth.lht.respose.mission;

import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.project.MissionsEO;
import lombok.Data;

import java.util.List;

/**
 * @program: lht
 * @description: 发布人的所有任务-->这些任务的所有情况
 * @author: Antony
 * @create: 2019-04-02 11:40
 **/
@Data
public class MissionLeaderVO {
    private MissionsEO missionsEO;
    private List<MissionUserEO> missionUserEOS;
}
