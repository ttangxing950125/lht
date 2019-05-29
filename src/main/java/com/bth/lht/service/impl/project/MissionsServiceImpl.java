package com.bth.lht.service.impl.project;

import com.bth.lht.dao.project.MissionsRepository;
import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.mission.MissionRequest;
import com.bth.lht.service.project.MissionsService;
import com.bth.lht.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: lht
 * @description: 任务层实现类
 * @author: Antony
 * @create: 2019-03-19 10:07
 **/
@Service
public class MissionsServiceImpl implements MissionsService {
    @Autowired
    private MissionsRepository missionsRepository;


    @Override
    public List<MissionsEO> findAllByOrderByCreateDateDesc() {
        return missionsRepository.findAllByOrderByCreateDateDesc();
    }

    @Override
    public List<MissionsEO> list() {
        return missionsRepository.findAll();
    }

    @Override
    public MissionsEO save(MissionsEO missionsEO) {

        return missionsRepository.save(missionsEO);
    }

    @Override
    public MissionsEO get(String id) {
        return missionsRepository.getOne(id);
    }

    @Override
    public List<MissionsEO> findByLeader(UserEO userEO) {

        return missionsRepository.findMissionsEOByLeaderEO(userEO);
    }




}
