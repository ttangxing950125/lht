package com.bth.lht.entity.project;

import com.bth.lht.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @program: lht
 * @description: 任务评论实体
 * @author: Antony
 * @create: 2019-04-01 17:11
 **/

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name = "tb_mission_comment")
public class MissionCommentEO extends BaseEntity {
    private String openid;//用户身份 openid
    private String content;//评论内容
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id",referencedColumnName = "id")
    private MissionsEO missionsEO;
}
