package com.bth.lht.entity.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.bth.lht.entity.BaseEntity;
import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.team.TeamUserEO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: lht
 * @description: 用户表
 * @author: Antony
 * @create: 2018-12-21 17:57
 **/
@Data
@ToString(exclude = {"userInfoEO","createMissionEOS","joinMissionsEOS","missionUserEOS"})
@Entity
@Table(name = "tb_wx_user")
public class UserEO extends BaseEntity {
    @Column(name = "wx_openid",nullable = false,updatable = false)
    private String wxOpenid;
    @Column(name = "wx_nickName",nullable = false)
    private String wxNickName;
    @Column(name = "avatar_url",nullable = false)
    private String avatarUrl;
    @Column(name = "integral",nullable = false)
    private BigDecimal integral;
    @Column(name = "phoneNumber")
    private String phoneNumber;




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id")
    private UserInfoEO userInfoEO;

    @JSONField
    @OneToMany(mappedBy = "leaderEO",fetch = FetchType.LAZY)
    private List<MissionsEO> createMissionEOS;

//    @ManyToMany(mappedBy = "userEOList")
//    private List<MissionsEO> joinMissionsEOS;
    @OneToMany(mappedBy = "userEO")
    private List<MissionUserEO> missionUserEOS;

    //领导者的团队
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TeamEO> leaderTeamEOS;
    //用户加入的团队
    @OneToMany(mappedBy = "userEO")
    private List<TeamUserEO> teamUserEOS;


    @JsonBackReference
    public void setMissionUserEOS(List<MissionUserEO> missionUserEOS) {
        this.missionUserEOS = missionUserEOS;
    }
    @JsonBackReference
    public void setLeaderTeamEOS(List<TeamEO> leaderTeamEOS) {
        this.leaderTeamEOS = leaderTeamEOS;
    }
    @JsonBackReference
    public void setTeamUserEOS(List<TeamUserEO> teamUserEOS) {
        this.teamUserEOS = teamUserEOS;
    }



    @JsonBackReference
    public void setCreateMissionEOS(List<MissionsEO> createMissionEOS) {
        this.createMissionEOS = createMissionEOS;
    }
}
