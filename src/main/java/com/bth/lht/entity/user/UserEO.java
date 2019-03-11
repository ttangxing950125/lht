package com.bth.lht.entity.user;

import com.bth.lht.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @program: lht
 * @description: 用户表
 * @author: Antony
 * @create: 2018-12-21 17:57
 **/
@Data
@ToString(exclude = {"userInfoEO"})
@Entity
@Table(name = "tb_wx_user")
public class UserEO extends BaseEntity {
    @Column(name = "wx_openid",nullable = false,updatable = false)
    private String wxOpenid;
    @Column(name = "wx_nickName",nullable = false)
    private String wxNickName;
    @Column(name = "avatar_url",nullable = false)
    private String avatarUrl;
    @Column(name = "integral",nullable = false)
    private BigDecimal integral;
    @Column(name = "phoneNumber")
    private Integer phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id")
    private UserInfoEO userInfoEO;

}
