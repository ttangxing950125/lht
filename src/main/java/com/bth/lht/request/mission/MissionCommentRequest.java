package com.bth.lht.request.mission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: lht
 * @description: 任务评论请求实体
 * @author: Antony
 * @create: 2019-04-01 18:59
 **/
@Data
@ApiModel("评论实体")
public class MissionCommentRequest {
    @ApiModelProperty("评论内容")
    private String content;//评论内容
    @ApiModelProperty("所评论的任务")
    private String MissionId;//任务id
    @ApiModelProperty("评论者")
    private String commentUser;
}
