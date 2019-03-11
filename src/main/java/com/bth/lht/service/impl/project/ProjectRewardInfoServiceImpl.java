package com.bth.lht.service.impl.project;

import com.bth.lht.dao.project.ProjectRewardInfoRepository;
import com.bth.lht.entity.project.ProjectRewardInfoEO;
import com.bth.lht.service.project.ProjectRewardInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: lht
 * @description: service实现 项目激励
 * @author: Antony
 * @create: 2018-12-21 19:48
 **/
@Service
@Slf4j
public class ProjectRewardInfoServiceImpl implements ProjectRewardInfoService {
    @Autowired
    private ProjectRewardInfoRepository projectRewardInfoRepository;


    @Override
    public ProjectRewardInfoEO save(ProjectRewardInfoEO projectRewardInfoEO) {
        return projectRewardInfoRepository.save(projectRewardInfoEO);
    }
}
