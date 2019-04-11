package com.bth.lht.rest.teamController;

import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.team.TeamUserEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.respose.base.MultiResponse;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.TeamUserService;
import com.bth.lht.service.team.TeamService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.TokenUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: lht
 * @description: 用户团队控制层
 * @author: Antony
 * @create: 2019-04-04 17:44
 **/
@Api("用户加入团队接口")
@RestController
@RequestMapping("teamUser")
public class TeamUserController extends BaseController {
    @Autowired
    private TeamUserService teamUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    /**
     * 用户加入团队
     */
    @PostMapping("userJoinTeam/{teamId}")
    public OneResponse userJoinTeam(@RequestHeader("token")String token, @PathVariable("teamId")String teamId){
        String openid = TokenUtil.getUserOpenidByToken(token);
        String res;
        UserEO userEO = userService.findByOpenid(openid);
        TeamEO teamEO = teamService.getById(teamId);
        //判断是否已经加入
        TeamUserEO t = teamUserService.findTeamUserEOSByUserEOAndTeamEO(userEO,teamEO);
        if (t!=null){
            //用户加入团队对象
            TeamUserEO teamUserEO = new TeamUserEO();
            teamUserEO.setStatus("waiting");
            teamUserEO.setTeamEO(teamEO);
            teamUserEO.setUserEO(userEO);
            teamUserService.add(teamUserEO);
            res = "添加成功";
        }else {
            //加载用户
            res = "已经加入过了";
        }

        return successOne(res);

    }

    /**
     *
     */


}
