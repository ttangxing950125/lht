package com.bth.lht.respose.mission;

import com.bth.lht.respose.wxUser.UserVO;
import lombok.Data;

import java.util.List;

@Data
public class MissionUserAppovalVO {

    private  String id;
    //任务标题
    private String  missionTitle;
    //任务简述
    private String missionInfo;
    //任务类型
    private String missionType;

    private String status;
    //接受任务的人
    private List<UserVO> userVOS;


}
