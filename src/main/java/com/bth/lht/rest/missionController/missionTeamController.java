package com.bth.lht.rest.missionController;

import com.bth.lht.entity.project.MissionTeamEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.team.TeamJoinMissionRequest;
import com.bth.lht.respose.base.BaseResponse;
import com.bth.lht.respose.base.MultiResponse;
import com.bth.lht.respose.mission.MissionTeamVO;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.project.MissionTeamService;
import com.bth.lht.service.project.MissionsService;
import com.bth.lht.service.team.TeamService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.ModelMapperUtil;
import com.bth.lht.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: lht
 * @description: 团队接收任务 控制层
 * @author: Antony
 * @create: 2019-04-04 00:44
 **/
@Api("团队接收任务接口")
@RestController
@RequestMapping("missionTeam")
public class missionTeamController extends BaseController {
    @Autowired
    private MissionTeamService missionTeamService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private MissionsService missionsService;

    /**
     * 团队身份接收任务
     * @param token token
     * @param request 请求实体（任务id ，团队id）
     * @return
     */
    @ApiOperation("团队身份接受任务")
    @PostMapping("joinByTeam")
    public BaseResponse add(@RequestHeader("token")String token,@Validated @RequestBody TeamJoinMissionRequest request){
        TeamEO teamEO = teamService.getById(request.getTeamId());
        MissionsEO missionsEO = missionsService.get(request.getMissionId());
        if (teamEO!=null && missionsEO!=null){
            MissionTeamEO missionTeamEO = new MissionTeamEO();
            missionTeamEO.setStatus("doing");
            missionTeamEO.setMissionsEO(missionsEO);
            missionTeamEO.setTeamEO(teamEO);
            missionTeamService.save(missionTeamEO);
        }
        return new BaseResponse();
    }

    @ApiOperation("查找自己所在团队的接收的任务")
    @GetMapping("getTeamMission")
    public MultiResponse listTeamMission(@RequestHeader("token") String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        //当前用户
        UserEO userEO = userService.findByOpenid(openid);
        List<TeamEO> teamEOS = teamService.findByLeader(userEO);
        //存储容器
        List<MissionTeamEO> result = new ArrayList<>();
        //暂存容器
        List<MissionTeamEO> missionTeamEOS = new ArrayList<>();
        for (TeamEO t:teamEOS
             ) {
            missionTeamEOS = missionTeamService.getMissionTeamEO(t);
            System.out.println(missionTeamEOS.size()+"个");
            if (missionTeamEOS.size()>0){
                System.out.println("查到啦！！！！！    ");
                //若查到该团队有接收任务，则放入存储容器
                //将暂存容器集合追加到存储容器
                for (int i = 0;i<missionTeamEOS.size();i++){
                    result.add(missionTeamEOS.get(i));
                }
            }
        }
        //映射为响应实体集合

        List<MissionTeamVO> missionTeamVOS = ModelMapperUtil.getStrictModelMapper().map(teamEOS,new TypeToken<List<MissionTeamEO>>(){}.getType());
        return successMulti(missionTeamVOS);
    }



}
