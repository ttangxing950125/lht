package com.bth.lht.entity.project;

import com.bth.lht.entity.BaseEntity;
import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.user.UserEO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @program: lht
 * @description: 团队接收任务实体
 * @author: Antony
 * @create: 2019-04-04 00:25
 **/
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name = "tb_mission_team")
public class MissionTeamEO extends BaseEntity {
    @Column(name = "status")
    private String status;

    @ManyToOne(targetEntity = MissionsEO.class)
    @JoinColumn(name = "mission_id",referencedColumnName = "id")
    private MissionsEO missionsEO;

    @ManyToOne(targetEntity = TeamEO.class)
    @JoinColumn(name = "team_id",referencedColumnName = "id")
    private TeamEO teamEO;
}
