package com.bth.lht.respose.mission;

import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.team.TeamEO;
import lombok.Data;

/**
 * @program: lht
 * @description: 团队接收任务响应实体
 * @author: Antony
 * @create: 2019-04-04 01:58
 **/
@Data
public class MissionTeamVO {
    private MissionsEO missionsEO;
    private String status;
    private TeamEO teamEO;
}
