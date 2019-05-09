package com.bth.lht.dao.project;

import com.bth.lht.entity.project.UserComments;
import com.bth.lht.entity.user.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCommentRepository extends JpaRepository<UserComments,String> {

    //查找用户所有的完成任务的的评价

    List<UserComments> findAllByUserEOOrderByCommentLevelDesc(UserEO userEO);
    //查找单个的任务的的评论
    UserComments findByAndCommentUserAndUserEO(UserEO commentuserEO,UserEO userEO);

}
