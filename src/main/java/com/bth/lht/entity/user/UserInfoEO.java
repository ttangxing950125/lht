package com.bth.lht.entity.user;

import com.bth.lht.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: lht
 * @description: 用户信息实体（类型，账号等信息）
 * @author: Antony
 * @create: 2018-12-21 13:58
 **/
@Data
@ToString(exclude = {"userEO"})
@Entity
@Table(name = "tb_user_info")
@EntityListeners(AuditingEntityListener.class)
public class UserInfoEO extends BaseEntity implements Serializable {
    @Column(name = "info_username")
    private String username;
    @Column(name = "info_password")
    private String password;

    @OneToOne(mappedBy = "userInfoEO")
    private UserEO userEO;

}
