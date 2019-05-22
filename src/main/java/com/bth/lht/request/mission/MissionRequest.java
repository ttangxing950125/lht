package com.bth.lht.request.mission;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @program: lht
 * @description: 任务请求类
 * @author: Antony
 * @create: 2019-03-19 10:02
 **/
@Data
@ApiModel("任务请求对象")
public class MissionRequest {
    //任务标题
    @ApiModelProperty("任务标题")
    private String  missionTitle;
    //任务简述
    private String missionInfo;
    //任务背景
    private String missionStory;
    //任务响应时间
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private String missionResponseStopTime;
    //任务开始时间
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private String missionStartTime;
    //任务结束时间
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
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

    private int missionLevel;
}
