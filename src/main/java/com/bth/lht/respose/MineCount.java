package com.bth.lht.respose;

import lombok.Data;

/**
 *  我的信息页面任务 团队 相关数量实体类
 *
 */
@Data
public class MineCount {
    //我接受任务的数量
    private int mimeMissionCount;
    //我的团队数量
    private int mineTeamCount;
    //我发布的任务的数量
    private int minePublishCount;
    //团队任务的数量
    private int mineTeamMissionCount;
    //我审批的任务的数量
    private int mineMissionApproval;
    //我的团队申请审批的数量
    private  int mineTeamApproval;



}
