package com.bth.lht.entity.project;

import com.bth.lht.entity.BaseEntity;
import com.bth.lht.entity.user.UserEO;
import lombok.Data;

import javax.persistence.*;

/**
 * @program: lht
 * @description: 用户接任务  中间表
 * @author: Antony
 * @create: 2019-04-01 21:15
 **/
@Data
@Entity
@Table(name = "tb_mission_user")
public class MissionUserEO extends BaseEntity {
    @Column(name = "status")
    private String status;

    @ManyToOne(targetEntity = MissionsEO.class)
    @JoinColumn(name = "mission_id",referencedColumnName = "id")
    private MissionsEO missionsEO;

    @ManyToOne(targetEntity = UserEO.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEO userEO;
}
