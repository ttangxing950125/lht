package com.bth.lht.request.team;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: lht
 * @description: 添加团队---实体
 * @author: Antony
 * @create: 2019-04-03 13:13
 **/
@Data
public class TeamRequest {
    //队名
    private String teamName;
    //团队擅长领域
    private String teamTechnology;
    //团队介绍，服务类型
    private String teamDesc;
    //团队简介
    private String teamInfo;
}
