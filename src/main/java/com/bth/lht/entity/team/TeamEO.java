package com.bth.lht.entity.team;

import com.bth.lht.entity.BaseEntity;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;

import javax.persistence.*;
import java.util.List;

/**
 * @program: lht
 * @description: 团队实体类
 * @author: Antony
 * @create: 2019-03-21 09:47
 **/

@Entity
@Table(name = "tb_team")
public class TeamEO extends BaseEntity {
    @Column(name = "team_name",nullable = false)
    private String teamName;

    @ManyToMany(mappedBy = "teamEOList")
    private List<MissionsEO> missionsEOList;

    //团队队长与团队用户 多对一
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_openid",referencedColumnName = "wx_openid")
    private UserEO userEO;


}
