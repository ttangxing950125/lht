package com.bth.lht.respose;

import lombok.Data;

import java.util.Date;

@Data
public class UserCommentResponse {


    private String id;

    private Date create_date;

    private  Date modify_date;

    private String commemt_text;

    private  String comment_userid;

    private String comment_mission_id;

    private  String userid;

    private int comment_level;
}
