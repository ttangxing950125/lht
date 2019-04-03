package com.bth.lht.request.mission;

import lombok.Data;

/**
 * @program: lht
 * @description: 用户-任务 更新请求
 * @author: Antony
 * @create: 2019-04-02 16:30
 **/
@Data
public class UpdateMissionUserRequest {
    String missionId;
    String category;
}
