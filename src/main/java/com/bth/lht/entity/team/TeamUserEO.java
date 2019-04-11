package com.bth.lht.entity.team;

import com.bth.lht.entity.BaseEntity;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @program: lht
 * @description: 用户加入团队实体
 * @author: Antony
 * @create: 2019-04-04 17:33
 **/
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Data
@Entity
@ToString(exclude = {"teamEO","userEO"})
@Table(name = "tb_team_user")
public class TeamUserEO extends BaseEntity {
    @Column(name = "status")
    private String status;

    @ManyToOne(targetEntity = TeamEO.class)
    @JoinColumn(name = "team_id",referencedColumnName = "id")
    private TeamEO teamEO;

    @ManyToOne(targetEntity = UserEO.class)
   @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEO userEO;
}
