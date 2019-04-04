package com.bth.lht.request.team;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @program: lht
 * @description: 团队接收任务请求
 * @author: Antony
 * @create: 2019-04-04 00:17
 **/
@Data
@ApiModel("团队接收任务请求实体")
public class TeamJoinMissionRequest {
    //团队id
    String teamId;
    //任务id
    String missionId;

}
