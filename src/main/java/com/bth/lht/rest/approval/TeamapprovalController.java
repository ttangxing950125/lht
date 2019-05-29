package com.bth.lht.rest.approval;

import com.bth.lht.entity.project.MissionTeamEO;
import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.team.TeamUserEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.mission.MissionApprovalRequest;
import com.bth.lht.request.team.TeamUserJoinRequest;
import com.bth.lht.respose.base.MultiResponse;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.respose.mission.MissionTeamAppovalVO;
import com.bth.lht.respose.mission.MissionTeamVO;
import com.bth.lht.respose.mission.MissionUserAppovalVO;
import com.bth.lht.respose.mission.MissionVO;
import com.bth.lht.respose.team.TeamUserVO;
import com.bth.lht.respose.team.TeamVO;
import com.bth.lht.respose.wxUser.UserVO;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.TeamUserService;
import com.bth.lht.service.project.MissionTeamService;
import com.bth.lht.service.project.MissionUserService;
import com.bth.lht.service.project.MissionsService;
import com.bth.lht.service.team.TeamService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.ModelMapperUtil;
import com.bth.lht.util.TokenUtil;
import io.swagger.annotations.Api;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/***
 * 审批功能控制层接口
 */
@RestController
@RequestMapping("approval")
@Api("审批列表")
public class TeamapprovalController extends BaseController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;

    @Autowired
    private TeamUserService teamUserService;
    @Autowired
    private MissionUserService missionUserService;
    @Autowired
    private MissionsService missionsService;
    @Autowired
    private MissionTeamService missionTeamService;

    /**
     * 团队审批列表
     *
     * @param
     * @return
     */
    @GetMapping("findAllTeam")
    private MultiResponse<TeamVO> findAllTeam(@RequestHeader("token") String token) {
        String openid = TokenUtil.getUserOpenidByToken(token);
        UserEO userEO = userService.findByOpenid(openid);

        //获得当前用户所有的团队
        List<TeamEO> teamEOS = teamService.findByLeader(userEO);
        List<TeamVO> teamVOS = ModelMapperUtil.getStrictModelMapper().map(teamEOS, new TypeToken<List<TeamVO>>() {
        }.getType());
        //查询当前团队的审批
        List<TeamUserEO> byTeamEO;
        //当前团队的yonghu
        List<UserEO> userEOS;

        for (int i = 0; i < teamVOS.size(); i++) {
            byTeamEO = teamUserService.findByTeamEO(teamEOS.get(i));
            userEOS = new ArrayList<>();
            for (TeamUserEO teamUser : byTeamEO
            ) {
                userEOS.add(teamUser.getUserEO());
            }
            List<UserVO> userVOS = ModelMapperUtil.getStrictModelMapper().map(userEOS, new TypeToken<List<UserVO>>() {
            }.getType());
            teamVOS.get(i).setUserVOS(userVOS);
        }

        return successMulti(teamVOS);
    }

    //找到所有的任务申请
    @GetMapping("findAllUserApply")
    public MultiResponse findAll(@RequestHeader("token") String token) {
        String openid = TokenUtil.getUserOpenidByToken(token);
        UserEO userEO = userService.findByOpenid(openid);
        //查找当前用户发布的任务
        List<MissionsEO> missionsEOS = missionsService.findByLeader(userEO);
        List<MissionVO> missionVOS = ModelMapperUtil.getStrictModelMapper().map(missionsEOS, new TypeToken<List<MissionVO>>() {
                }.getType()
        );
        //查找提交当任务的相关的
        List<MissionUserEO> missionUserEOS;
        List<UserEO> userEOS;
        if (missionsEOS != null) {
            for (int i = 0; i < missionVOS.size(); i++
            ) {
                missionUserEOS = missionUserService.findMissionUserEOSByMissionEO(missionsEOS.get(i));
                userEOS = new ArrayList<>();

                for (MissionUserEO mu : missionUserEOS
                ) {
                    UserEO ue = mu.getUserEO();
                    userEOS.add(ue);
                }
                UserVO userVO = ModelMapperUtil.getStrictModelMapper().map(userEO, new TypeToken<UserVO>() {
                }.getType());
                List<UserVO> userVOS = ModelMapperUtil.getStrictModelMapper().map(userEOS, new TypeToken<List<UserVO>>() {
                }.getType());
                missionVOS.get(i).setUserVO(userVO);

            }

        }


        return successMulti(missionVOS);
    }

    //申请加入团队通过
    @PostMapping("joinTeam")
    public OneResponse arrgeeJoinTeam(@RequestHeader("token") String token, @RequestBody @Validated TeamUserJoinRequest tujr) {

        // String openid = TokenUtil.getUserOpenidByToken(token);
        //System.out.println("开始查找"+userid);
        UserEO userEO = userService.findById(tujr.getUserid());
        TeamEO teamEO = teamService.getById(tujr.getTeamid());
        TeamUserEO teamUserEOByUserEOAndTeamEO = teamUserService.findTeamUserEOByUserEOAndTeamEO(userEO, teamEO);
        teamUserEOByUserEOAndTeamEO.setStatus("done");
        TeamUserEO teamUserEO1 = teamUserService.update(teamUserEOByUserEOAndTeamEO);
        TeamUserVO teamUserVO = ModelMapperUtil.getStrictModelMapper().map(teamUserEO1, TeamUserVO.class);
        System.out.println(teamUserVO.toString());
        return successOne("OK");
    }

    //任务的审批通过
    @PostMapping("approvalMission")
    public OneResponse arrgreeMission(@RequestBody @Validated MissionApprovalRequest missionApprovalRequest) {

        MissionsEO missionsEO = new MissionsEO();

        if ("1".equals(missionApprovalRequest.getResult())){
            if (missionApprovalRequest!=null || missionApprovalRequest.getMissionId()!=null)
            {
                missionsEO = missionsService.get(missionApprovalRequest.getMissionId());

            }
            UserEO userEO = null;
            TeamEO teamEO = null;
            if (missionApprovalRequest.getUserId() != null) {
                userEO = userService.findById(missionApprovalRequest.getUserId());


                MissionUserEO missionUserEO = missionUserService.findMissionUserEOByUserEOAndMissionsEO(userEO, missionsEO);
                missionUserEO.setStatus("done");
//             missionVO = ModelMapperUtil.getStrictModelMapper(missionUserService.save(missionUserEO),new TypeToken<MissionVO>(){}.getType());

                return successOne(missionUserService.save(missionUserEO));
            } else if (missionApprovalRequest.getTeamId() != null) {
                teamEO = teamService.getById(missionApprovalRequest.getTeamId());
                MissionTeamEO missionTeamEO = missionTeamService.findByTeamEOAndMissionsEO(teamEO,missionsEO);
                missionTeamEO.setStatus("done");
                return successOne(missionTeamService.save(missionTeamEO));
            }
        }else {


            if (missionApprovalRequest!=null || missionApprovalRequest.getMissionId()!=null)
            {
                missionsEO = missionsService.get(missionApprovalRequest.getMissionId());

            }
            UserEO userEO = null;
            TeamEO teamEO = null;
            if (missionApprovalRequest.getUserId() != null) {
                userEO = userService.findById(missionApprovalRequest.getUserId());


                MissionUserEO missionUserEO = missionUserService.findMissionUserEOByUserEOAndMissionsEO(userEO, missionsEO);
                missionUserEO.setStatus("refuse");
//             missionVO = ModelMapperUtil.getStrictModelMapper(missionUserService.save(missionUserEO),new TypeToken<MissionVO>(){}.getType());

                return successOne(missionUserService.save(missionUserEO));
            } else if (missionApprovalRequest.getTeamId() != null) {
                teamEO = teamService.getById(missionApprovalRequest.getTeamId());
                MissionTeamEO missionTeamEO = missionTeamService.findByTeamEOAndMissionsEO(teamEO,missionsEO);
                missionTeamEO.setStatus("refuse");
                return successOne(missionTeamService.save(missionTeamEO));
            }
        }

        return  null;
    }

    /**
     *   //个人任务审批列表
     * @return
     */

        @GetMapping("UserMissionApproval")
    public MultiResponse getUserMissionApproval(@RequestHeader("token") String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        UserEO userEO = userService.findByOpenid(openid);

        //所有的任务
       List<MissionsEO> missionsEO =  missionsService.findByLeader(userEO);
       List<MissionUserAppovalVO> miossionUserAppovalVOS = ModelMapperUtil.getStrictModelMapper().map(missionsEO,new TypeToken<List<MissionUserAppovalVO>>(){}.getType());


        List<MissionUserEO> missionUserEOS =new ArrayList<>();
        List<UserEO> userEOS =new ArrayList<>();
        List<MissionUserEO> missionUserEOS1 ;



        if (missionsEO.size()>0) {
            for (int i = 0;i<missionsEO.size();i++) {
                missionUserEOS1 = missionUserService.findMissionUserEOSByMissionEO(missionsEO.get(i));
                if (missionUserEOS1.size() > 0) {
                    missionUserEOS.addAll(missionUserEOS1);
                    //找到所有的用户
                    if (missionUserEOS.size()>0){
                        for (MissionUserEO meo:missionUserEOS
                        ) {

                            userEOS.add(meo.getUserEO());
                            if (miossionUserAppovalVOS.get(i).getId().equals(meo.getMissionsEO().getId())){
                                miossionUserAppovalVOS.get(i).setStatus(meo.getStatus());
                            }
                        }
                        List<UserVO>  UserVOs = ModelMapperUtil.getStrictModelMapper().map(userEOS,new TypeToken<List<UserVO>>(){}.getType());
                        miossionUserAppovalVOS.get(i).setUserVOS(UserVOs);
                    }
                }
            }

//            List<UserVO> list = ModelMapperUtil.getStrictModelMapper().map(userEOS,new TypeToken<List<UserVO>>(){}.getType());
            //System.out.println(miossionUserAppovalVOS.toString());
            return successMulti(miossionUserAppovalVOS);
        }else {
            return  successMulti(miossionUserAppovalVOS);
        }
    }
/**
 * 团队任务审批列表
 */
    @GetMapping("teamMissionApproval")
    public MultiResponse getTeamMissionApproval(@RequestHeader("token") String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        //获得当前的用户
     UserEO userEO = userService.findByOpenid(openid);
     //查询当前用户发布的所有任务
        List<MissionsEO> missionsEOS = missionsService.findByLeader(userEO);
        //筛选出是团队任务的中间表
        List<MissionTeamEO> missionTeamEOS = new ArrayList<>();
        //
        List<MissionTeamAppovalVO> missionTeamAppovalVOS =ModelMapperUtil.getStrictModelMapper().map(missionsEOS,new TypeToken< List<MissionTeamAppovalVO>>(){}.getType());
        //找到所有的团队
        List<TeamEO> teamEOS = new ArrayList<>();
        List<TeamVO> teamVOS=new ArrayList<>();
        if (missionTeamAppovalVOS.size()>0) {
            for (int i = 0; i < missionTeamAppovalVOS.size(); i++) {
                //将关于这个任务的所有关联提都放在一起
                missionTeamEOS.addAll(missionTeamService.findAllByMissionsEO(missionsEOS.get(i)));

                if (missionsEOS.size() > 0) {
                    for (MissionTeamEO mt : missionTeamEOS
                    ) {
                        System.out.println("正在输出："+mt);
                        teamEOS.add(teamService.getById(mt.getTeamEO().getId()));
                        teamVOS = ModelMapperUtil.getStrictModelMapper().map(teamEOS, new TypeToken<List<TeamVO>>() {
                        }.getType());


                    }
                    missionTeamAppovalVOS.get(i).setTeamVOS(teamVOS);
                }
            }
        }
        return successMulti(missionTeamAppovalVOS);
   }


    /**
     * 关于我的团队任务
     */
    @GetMapping("myTeamMissions")
    public MultiResponse findAllTeamMissions(@RequestHeader("token") String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        //获得当前的用户
        UserEO userEO = userService.findByOpenid(openid);
        //根据用户找到所有的团队相关
        List<TeamUserEO> teamUsers = teamUserService.getByUserEOAndStatus(userEO,"done");
        List<TeamEO> teamEOS = new ArrayList<>();
        //找出对应团队

        for (TeamUserEO tu:teamUsers
             ) {
            teamEOS.add(teamService.getById(tu.getTeamEO().getId()));

        }
        List<MissionsEO> missionsEOS = new ArrayList<>();
        List<MissionTeamEO> missionTeamEOS = new ArrayList<>();
        //根据团队找到所有的任务
        if (teamEOS.size()>0){
        for (int i=0;i<teamEOS.size();i++){
            missionTeamEOS.addAll(missionTeamService.getMissionTeamEO(teamEOS.get(i)));
            }
        }
       List<MissionTeamVO> missionTeamVOS = ModelMapperUtil.getStrictModelMapper().map(missionsEOS,new TypeToken<List<MissionTeamVO>>(){}.getType());

        return successMulti(missionTeamVOS);
    }
}

