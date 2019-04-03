package com.bth.lht.entity.team;

import com.bth.lht.entity.BaseEntity;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @program: lht
 * @description: 团队实体类
 * @author: Antony
 * @create: 2019-03-21 09:47
 **/

@Data
@Entity
@Table(name = "tb_team")
public class TeamEO extends BaseEntity {
    //队名
    @Column(name = "team_name",nullable = false)
    private String teamName;
    //团队擅长领域
    @Column(name = "team_technology",nullable = false)
    private String teamTechnology;

    //团队介绍，服务类型
    @Column(name = "team_desc",nullable = false)
    private String teamDesc;
    //团队简介
    @Column(name = "team_info",nullable = false)
    private String teamInfo;


    //团队等级
    @Column(name = "level",nullable = false)
    private String level;




    @ManyToMany(mappedBy = "teamEOList")
    private List<MissionsEO> missionsEOList;

    //团队队长与团队用户 多对一
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_openid",referencedColumnName = "wx_openid")
    private UserEO userEO;


}
