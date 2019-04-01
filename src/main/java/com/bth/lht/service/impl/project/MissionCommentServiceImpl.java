package com.bth.lht.service.impl.project;

import com.bth.lht.dao.project.MissionCommentRepository;
import com.bth.lht.entity.project.MissionCommentEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.service.project.MissionCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: lht
 * @description: 任务评论控制
 * @author: Antony
 * @create: 2019-04-01 18:49
 **/
@Service
public class MissionCommentServiceImpl implements MissionCommentService {
    @Autowired
    private MissionCommentRepository commentRepository;
    @Override
    public List<MissionCommentEO> findMissionCommentEOSByMissionsEO(MissionsEO missionsEO) {
        return commentRepository.findMissionCommentEOSByMissionsEO(missionsEO);
    }

    @Override
    public MissionCommentEO save(MissionCommentEO commentEO) {
        return commentRepository.save(commentEO);
    }

    @Override
    public MissionCommentEO get(String id) {
        return commentRepository.getOne(id);
    }

    @Override
    public void delete(String id) {
        commentRepository.delete(this.get(id));
    }
}
