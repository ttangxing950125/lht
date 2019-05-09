package com.bth.lht.request.UserCommentRequest;

import lombok.Data;

@Data
public class UserCommentRequest {


    private String commentUserid;
    private String userid;

    private  String commentMissionid;

    private int connmentLevel;

    private String commentText;
}
