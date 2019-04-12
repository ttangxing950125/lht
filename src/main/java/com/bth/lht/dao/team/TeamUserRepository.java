package com.bth.lht.dao.team;

import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.team.TeamUserEO;
import com.bth.lht.entity.user.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: lht
 * @description: 用户加入团队实体
 * @author: Antony
 * @create: 2019-04-04 17:32
 **/
public interface TeamUserRepository extends JpaRepository<TeamUserEO,String> {
    //查找用户自己所在的团队
    List<TeamUserEO> findTeamUserEOByUserEO(UserEO userEO);
    //查找用户是否已经加入该团队
    TeamUserEO findTeamUserEOSByUserEOAndTeamEO(UserEO userEO , TeamEO teamEO);

    //通过用户以及审核状态查找记录
    TeamUserEO findTeamUserEOByUserEOAndStatus(UserEO userEO,String status);


    //通过状态查找用户自己加入的团队
    List<TeamUserEO> findTeamUserEOSByUserEOAndStatus(UserEO userEO,String status);

}
