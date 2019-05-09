package com.bth.lht.entity.project;

import com.bth.lht.entity.BaseEntity;
import com.bth.lht.entity.user.UserEO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_userComments")
public class UserComments extends BaseEntity {



    private String commemtText;//评论的内容

    private  int commentLevel;//任务打分

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_userid")
    private UserEO commentUser;//评论者id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="comment_missionId")
    private MissionsEO missionsEO;//评论任务Id

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private UserEO userEO;//任务完成者Id

    private String status;//评论状态


}
