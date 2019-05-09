package com.bth.lht.service.impl;

import com.bth.lht.dao.project.UserCommentRepository;
import com.bth.lht.entity.project.UserComments;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.service.project.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserCommentServiceImpl implements UserCommentService {
    @Autowired
    private UserCommentRepository commentRepository;
    @Override
    public UserComments get(String id) {
        return commentRepository.getOne(id);
    }

    @Override
    public UserComments save(UserComments userComments) {
        return commentRepository.save(userComments);
    }

    @Override
    public List<UserComments> findAllByUserEOOrderByCommentLevelDesc(UserEO userEO) {
        return commentRepository.findAllByUserEOOrderByCommentLevelDesc(userEO);
    }

    @Override
    public UserComments findByAndCommentUserAndUserEO(UserEO commentuserEO, UserEO userEO) {
        return commentRepository.findByAndCommentUserAndUserEO(commentuserEO,userEO);
    }


}
