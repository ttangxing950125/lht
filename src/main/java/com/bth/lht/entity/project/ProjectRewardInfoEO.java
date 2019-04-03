package com.bth.lht.entity.project;

import com.bth.lht.entity.BaseEntity;
import com.bth.lht.enums.Enums;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @program: lht
 * @description: 项目激励信息
 * @author: Antony
 * @create: 2018-12-21 19:27
 **/

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name = "tb_project_reward_info")
public class ProjectRewardInfoEO extends BaseEntity {
    @Column(name = "reward_type",nullable = false)
    private Enums.ProjectRewardType rewardType;
    //此处需注意数据库同步修改  有效位数 以及小数点后几位
    @Column(name = "reward_num",nullable = false,precision = 38,scale = 8)
    private BigDecimal rewardNum;

}
