package com.bth.lht.respose.mission;

import com.bth.lht.entity.project.MissionsEO;
import lombok.Data;


/**
 * @program: lht
 * @description: 用户/任务响应实体
 * @author: Antony
 * @create: 2019-04-02 10:35
 **/
@Data
public class MissionUserVO {
    private MissionsEO missionsEO;
    private String status;

}
