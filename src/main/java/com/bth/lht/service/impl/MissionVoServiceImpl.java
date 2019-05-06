package com.bth.lht.service.impl;

import com.bth.lht.dao.team.MissionDao;
import com.bth.lht.respose.mission.MissionVO;
import com.bth.lht.service.MissionVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionVoServiceImpl implements MissionVoService {

   @Autowired
   private MissionDao missionDao;
    @Override
    public List<MissionVO> findAllByTeamId(String id) {
        return missionDao.findAllByTeamId(id);
    }
}
