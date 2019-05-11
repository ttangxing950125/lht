package com.bth.lht.respose.mission;

import com.bth.lht.entity.user.UserEO;
import com.bth.lht.respose.wxUser.UserVO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @program: lht
 * @description: 任务响应对象
 * @author: Antony
 * @create: 2019-03-20 13:13
 **/
@Data
@ToString(exclude = {"userEO"})
public class MissionVO {
    private  String id;
    //任务标题
    private String  missionTitle;
    //任务简述
    private String missionInfo;
    //任务背景
    private String missionStory;
    //任务响应时间
    private String missionResponseStopTime;
    //任务开始时间
    private String missionStartTime;
    //任务结束时间
    private String missionEndTime;
    //任务奖励类型
    private String missionAwardType;
    //任务奖励数量
        private String missionAward;
    //任务类型
    private String missionType;
    //任务定金
    private String missionDeposit;
    //任务验收方式
    private String missionCheckType;
    //任务需求
    private String missionDemand;
    //任务验收标准
    private String missionCheckStandard;

    private UserVO userVO;
    //倒计时
    List<String> countDownList;

    //任务等级
    private int missionLevel;
}
