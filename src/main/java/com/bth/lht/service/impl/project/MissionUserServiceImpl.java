package com.bth.lht.service.impl.project;

import com.bth.lht.dao.project.MissionUserRepository;
import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.respose.mission.MissionLeaderVO;
import com.bth.lht.service.project.MissionUserService;
import com.bth.lht.service.project.MissionsService;
import com.bth.lht.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private UserService userService;
    @Autowired
    private MissionsService missionsService;

    /**
     * 通过用户和任务查找
     * @param userEO 用户
     * @param missionsEO 任务
     * @return
     */
    @Override
    public MissionUserEO findMissionUserEOByUserEOAndMissionsEO(UserEO userEO, MissionsEO missionsEO) {
        MissionUserEO missionUserEO = missionUserRepository.findMissionUserEOByUserEOAndMissionsEO(userEO, missionsEO);



        return missionUserEO;
    }

    @Override
    public List<MissionUserEO> findMissionUserEOSByUserEO(UserEO userEO) {
        return missionUserRepository.findMissionUserEOSByUserEO(userEO);
    }

    @Override
    public MissionUserEO save(MissionUserEO missionUserEO) {
        return missionUserRepository.save(missionUserEO);
    }

    @Override
    public void update(MissionUserEO missionUserEO) {
        missionUserRepository.save(missionUserEO);
    }

    //发布方查看自己发布的任务的状态

    @Override
    public List<MissionLeaderVO> findMissionLeaderVO(String leaderOpenid) {
        //发布人
        String openid=leaderOpenid;
        UserEO userEO = userService.findByOpenid(openid);
        //发布人的所有任务
        List<MissionsEO> missionsEOS = missionsService.findByLeader(userEO);
        //在用户接收任务表中查找所有当前任务

        //返回列表容器
        List<MissionLeaderVO> missionLeaderVOS = new ArrayList<>();
        //容器中的对象
        MissionLeaderVO missionLeaderVO = null;
        for (MissionsEO m:missionsEOS
             ) {
            missionLeaderVO = new MissionLeaderVO();
            missionLeaderVO.setMissionUserEOS(missionUserRepository.findMissionUserEOSByMissionsEO(m));
            missionLeaderVO.setMissionsEO(m);
            missionLeaderVOS.add(missionLeaderVO);
        }

        return missionLeaderVOS;
    }
    @Override
    public List<MissionUserEO> findMissionUserEOSByMissionEO(MissionsEO missionsEO) {
        return missionUserRepository.findMissionUserEOSByMissionsEO(missionsEO);
    }
}
