package com.bth.lht.respose.team;

import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.user.UserEO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @program: lht
 * @description: 用户加入团队的 实体
 * @author: Antony
 * @create: 2019-04-12 09:45
 **/
@Data
public class TeamUserVO {
    private String status;

    private TeamEO teamEO;

    private UserEO userEO;

}
