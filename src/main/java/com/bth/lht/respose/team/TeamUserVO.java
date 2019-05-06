package com.bth.lht.respose.team;

import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.respose.wxUser.UserInfoVO;
import com.bth.lht.respose.wxUser.UserVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * @program: lht
 * @description: 用户加入团队的 实体
 * @author: Antony
 * @create: 2019-04-12 09:45
 **/
@Data
public class TeamUserVO {
    private String status;

    private TeamVO teamVO;

    private UserVO userVO;



}