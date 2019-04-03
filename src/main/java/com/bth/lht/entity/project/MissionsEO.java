package com.bth.lht.entity.project;

import com.alibaba.fastjson.annotation.JSONField;
import com.bth.lht.entity.BaseEntity;
import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.user.UserEO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: lht
 * @description: 任务表
 * @author: Antony
 * @create: 2019-03-19 09:42
 **/

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
@Data
@ToString(exclude = {"userEO","leaderEO","missionCommentEOS","missionUserEOS"})
@Table(name="tb_missions")
public class MissionsEO extends BaseEntity implements Serializable {
    //任务标题
    @Column(name = "mission_title",nullable = true)
    private String  missionTitle;
    //任务简述
    @Column(name = "mission_info",nullable = true)
    private String missionInfo;
    //任务背景
    @Column(name = "mission_story",nullable = true)
    private String missionStory;
    //任务响应时间
    @Column(name = "mission_response_stop_time",nullable = true)
    private String missionResponseStopTime;
    //任务开始时间
    @Column(name = "mission_start_time",nullable = true)
    private String missionStartTime;
    //任务结束时间
    @Column(name = "mission_end_time",nullable = true)
    private String missionEndTime;
    //任务奖励类型
    @Column(name = "mission_award_type",nullable = true)
    private String missionAwardType;
    //任务奖励数量
    @Column(name = "mission_award",nullable = true)
    private String missionAward;
    //任务类型
    @Column(name = "mission_type",nullable = true)
    private String missionType;
    //任务定金
    @Column(name = "mission_deposit",nullable = true)
    private String missionDeposit;
    //任务验收方式
    @Column(name = "mission_check_type",nullable = true)
    private String missionCheckType;
    //任务需求
    @Column(name = "mission_demand",nullable = true)
    private String missionDemand;
    //任务验收标准
    @Column(name = "mission_check_standard",nullable = true)
    private String missionCheckStandard;
    //任务等级
    @Column(name = "mission_level",nullable = true)
    private int missionLevel;

    //发起人
    @JSONField(serialize = false)
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "leader_id",referencedColumnName = "id")
    private UserEO leaderEO;

//    //接受人
//    @ManyToMany
//    @JoinTable(name = "tb_mission_user",joinColumns={@JoinColumn(name = "mission_id",referencedColumnName = "id"),@JoinColumn(name = "mission_title",referencedColumnName = "Mission_title")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id"),@JoinColumn(name = "wx_nickName",referencedColumnName = "wx_nickName")})
//    private List<UserEO> userEOList;

    @OneToMany(mappedBy = "missionsEO")
    private List<MissionUserEO> missionUserEOS;

    //接收团队
    @ManyToMany
    @JoinTable(name = "tb_mission_team",joinColumns = {@JoinColumn(name = "mission_id",referencedColumnName = "id"),@JoinColumn(name="mission_title",referencedColumnName = "mission_title")},
    inverseJoinColumns = {@JoinColumn(name = "team_id",referencedColumnName = "id"),@JoinColumn(name = "team_name",referencedColumnName = "team_name")})
    private List<TeamEO> teamEOList;

    //评论
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="missionsEO" )
    private List<MissionCommentEO> missionCommentEOS;

    @JsonBackReference
    public void setLeaderEO(UserEO leaderEO) {
        this.leaderEO = leaderEO;
    }

    @JsonBackReference
    public void setMissionUserEOS(List<MissionUserEO> missionUserEOS) {
        this.missionUserEOS = missionUserEOS;
    }

    @JsonBackReference
    public void setTeamEOList(List<TeamEO> teamEOList) {
        this.teamEOList = teamEOList;
    }


    @JsonBackReference
    public void setMissionCommentEOS(List<MissionCommentEO> missionCommentEOS) {
        this.missionCommentEOS = missionCommentEOS;
    }
}
