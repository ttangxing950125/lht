package com.bth.lht.service.impl.project;

import com.bth.lht.dao.project.MissionUserRepository;
import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.service.project.MissionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: lht
 * @description: 用户接受任务 业务实现层
 * @author: Antony
 * @create: 2019-04-01 21:44
 **/
@Service
public class MissionUserServiceImpl implements MissionUserService {
    @Autowired
    private MissionUserRepository missionUserRepository;
    @Override
    public List<MissionUserEO> findMissionUserEOSByUserEO(UserEO userEO) {
        return missionUserRepository.findMissionUserEOSByUserEO(userEO);
    }

    @Override
    public MissionUserEO save(MissionUserEO missionUserEO) {
        return missionUserRepository.save(missionUserEO);
    }
}
