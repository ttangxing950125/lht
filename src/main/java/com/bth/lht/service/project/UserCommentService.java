package com.bth.lht.service.project;

import com.bth.lht.entity.project.UserComments;
import com.bth.lht.entity.user.UserEO;

import java.util.List;

public interface UserCommentService {
    UserComments get(String id);
    UserComments save(UserComments userComments);
    List<UserComments> findAllByUserEOOrderByCommentLevelDesc(UserEO userEO);
    //查找单个的任务的的评论
    UserComments findByAndCommentUserAndUserEO(UserEO commentuserEO,UserEO userEO);
}
