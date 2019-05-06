package com.bth.lht.respose.team;

import com.bth.lht.respose.mission.MissionVO;
import com.bth.lht.respose.wxUser.UserInfoVO;
import com.bth.lht.respose.wxUser.UserVO;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

/**
 * @program: lht
 * @description: 团队 响应实体
 * @author: Antony
 * @create: 2019-04-03 15:56
 **/
@Data
public class TeamVO {
    private String id;
    //队名
    private String teamName;
    //团队擅长领域
    private String teamTechnology;

    //团队介绍，服务类型
    private String teamDesc;
    //团队简介
    private String teamInfo;
    //团队等级
    private String level;
    //团队人数
    private int peopleCount;

    List<MissionVO> missionVOS;

    //该团队所有的用户申请列表
    List<UserVO> userVOS;

    //成交量
    private int makeDeal;
    //成交总额
    private double makeMoney;

    public void setTeamDesc(String teamDesc) {
        if (teamDesc.length()>12){
            teamDesc = teamDesc.substring(0,12)+"...";
        }
        this.teamDesc = teamDesc;
    }
}
