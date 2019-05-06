package com.bth.lht.rest.missionController;

import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.mission.UpdateMissionUserRequest;
import com.bth.lht.request.team.TeamJoinMissionRequest;
import com.bth.lht.respose.base.BaseResponse;
import com.bth.lht.respose.base.MultiResponse;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.respose.mission.MissionLeaderVO;
import com.bth.lht.respose.mission.MissionUserVO;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.project.MissionUserService;
import com.bth.lht.service.project.MissionsService;
import com.bth.lht.service.team.TeamService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.ModelMapperUtil;
import com.bth.lht.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import org.apache.catalina.User;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: lht
 * @description: 用户/任务控制层
 * @author: Antony
 * @create: 2019-04-02 10:32
 **/
@RestController
@RequestMapping("missionUser")
@Api("用户所接受的任务相关接口")
public class MissionUserController extends BaseController {
    @Autowired
    private MissionUserService missionUserService;
    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MissionsService missionsService;


    /**
     * 个人身份-->接受任务，重新添加任务，但leaderID不能修改，为任务加上用户openid或者团队id 多对多
     * @return
     */
    @PostMapping("joinByUser/{id}")
    @ApiOperation("接受任务")
    public OneResponse joinByUser(@PathVariable("id")String id, @RequestHeader("token")String token){
        String res= "";
        MissionUserEO missionUserEO = new MissionUserEO();
        String openid = TokenUtil.getUserOpenidByToken(token);
        //得到该用户
        UserEO userEO = userService.findByOpenid(openid);
        //接收请求对象，并映射为实体
        MissionsEO missionsEO = missionsService.get(id);
        if (missionsEO.getLeaderEO().equals(userEO)){
            res = "不可以领取自己发布的任务哟";
            return successOne(res);
        }
        MissionUserEO mu = missionUserService.findMissionUserEOByUserEOAndMissionsEO(userEO,missionsEO);
        if (mu!=null){
            res = "你已经领取该任务了，赶紧去领取其他任务吧";
            return successOne(res);
        }
        if (missionsEO!=null) {
            //接收任务的团队或者个人
            missionUserEO.setStatus("doing");
            missionUserEO.setMissionsEO(missionsEO);
            missionUserEO.setUserEO(userEO);
            missionUserService.save(missionUserEO);
            res = "添加任务成功！！";

        }else {
            System.out.println("没找到任务");
            res = "没找到任务！！";

        }
        return successOne(res);
    }



        /**
         * 按类别查找查找任务 进行中 待审核 已完成 0 1 2
         */
    @GetMapping("getUserMission")
    @ApiOperation("查找用户接受过的任务")
    public MultiResponse listMyMission(@RequestHeader("token")String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        List<MissionUserEO> missionUserEOS = missionUserService.findMissionUserEOSByUserEO(userService.findByOpenid(openid));
        List<MissionUserVO> missionUserVOS = ModelMapperUtil.getStrictModelMapper().
                map(missionUserEOS,new TypeToken<List<MissionUserVO>>(){}.getType());
        return successMulti(missionUserVOS);
    }

    /**
     *
     * 更改用户所接受的任务的状态
     */
    @PostMapping("updateUserMission")
    @ApiOperation("更改用户所接受的任务的状态")
    public BaseResponse update(@RequestHeader("token") String token, @RequestBody @Validated UpdateMissionUserRequest request){
        System.out.println(request.toString());

        String openid = TokenUtil.getUserOpenidByToken(token);
        MissionsEO missionsEO = missionsService.get(request.getMissionId());
        MissionUserEO missionUserEO = missionUserService.findMissionUserEOByUserEOAndMissionsEO(userService.findByOpenid(openid), missionsEO);
        //修改
        if (missionsEO!=null){
            missionUserEO.setStatus(request.getCategory());
        }
        missionUserService.update(missionUserEO);
        return  new BaseResponse();
    }


    /**
     * 发布人查看自己发布的被接收的状态
     */
    @ApiOperation("发布人查看自己所发布的任务接收情况")
    @GetMapping("listLeaderMission")
    public MultiResponse list(@RequestHeader("token")String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
       List<MissionLeaderVO> missionLeaderVOS = missionUserService.findMissionLeaderVO(openid);
        return successMulti(missionLeaderVOS);
    }


    @ApiOperation("通过用户以及任务id查找当前任务")
    @GetMapping("getByUserAndMission/{missionId}")
    public OneResponse get(@PathVariable("missionId")String id,@RequestHeader("token")String token){
        MissionsEO missionsEO = missionsService.get(id);
        UserEO userEO = userService.findByOpenid(TokenUtil.getUserOpenidByToken(token));
        MissionUserEO missionUserEO = missionUserService.findMissionUserEOByUserEOAndMissionsEO(userEO,missionsEO);
        MissionUserVO missionUserVO = ModelMapperUtil.getStrictModelMapper().map(missionUserEO,MissionUserVO.class);
        System.out.println(missionUserVO.toString());
        return successOne(missionUserVO);
    }

    @ApiOperation("查找某任务的接收情况")
    @GetMapping("getByMission/{missionId}")
    public MultiResponse getByMission(@PathVariable("missionId")String missionId,@RequestHeader("token")String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        MissionsEO missionsEO = missionsService.get(missionId);
        List<MissionUserEO> missionUserEOS =missionUserService.findMissionUserEOSByMissionEO(missionsEO);
        List<MissionUserVO> missionUserVOS = ModelMapperUtil.getStrictModelMapper().map(missionUserEOS,new TypeToken<List<MissionUserVO>>(){}.getType());
        return successMulti(missionUserVOS);
    }




}
