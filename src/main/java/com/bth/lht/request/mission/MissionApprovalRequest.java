package com.bth.lht.request.mission;

import lombok.Data;

@Data
public class MissionApprovalRequest {

    private  String missionId;
    private String userId;
    private String teamId;
    private  String result;
}
