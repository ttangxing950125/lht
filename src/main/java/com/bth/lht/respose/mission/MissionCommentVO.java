package com.bth.lht.respose.mission;

import com.bth.lht.entity.project.MissionsEO;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @program: lht
 * @description: 任务评论响应实体
 * @author: Antony
 * @create: 2019-04-01 18:52
 **/
@Data
public class MissionCommentVO {
    private String creatTime;//时间
    private String content;//评论内容
    private String  commentUser;//评论人


}
