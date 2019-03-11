package com.bth.lht.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: lht
 * @description: 实体基类
 * @author: Antony
 * @create: 2018-12-21 13:54
 **/
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid",strategy = "uuid")
    private String id;
    @CreatedDate
    @Column(name = "create_date",nullable = false,updatable = false)
    private Date createDate;
    @LastModifiedDate
    @Column(name = "modify_date",nullable = false)
    private Date modifyDate;

}
