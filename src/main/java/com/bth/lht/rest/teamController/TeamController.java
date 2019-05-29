package com.bth.lht.rest.teamController;
import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.team.TeamRequest;
import com.bth.lht.respose.base.BaseResponse;
import com.bth.lht.respose.base.MultiResponse;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.respose.mission.MissionVO;
import com.bth.lht.respose.team.TeamVO;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.MissionVoService;
import com.bth.lht.service.TeamMissionService;
import com.bth.lht.service.TeamUserService;
import com.bth.lht.service.TeamVoService;
import com.bth.lht.service.project.MissionTeamService;
import com.bth.lht.service.team.TeamService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.ModelMapperUtil;
import com.bth.lht.util.TokenUtil;
import io.swagger.annotations.Api;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: lht
 * @description: 团队控制层
 * @author: Antony
 * @create: 2019-04-03 11:48
 **/
@Api("团队相关接口")
@RestController
@RequestMapping("team")
public class TeamController extends BaseController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamUserService teamUserService;

    @Autowired
    private MissionTeamService missionTeamService;
    @Autowired
    private TeamVoService teamVoService;
    @Autowired
    private TeamMissionService tms;
    @Autowired
    private MissionVoService missionVoService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     *  用户添加团队
     * @param teamRequest
     * @param token
     * @return
     */
    @PostMapping("add")
    public BaseResponse add(@RequestBody @Validated TeamRequest teamRequest, @RequestHeader("token") String token) {

        String openid = TokenUtil.getUserOpenidByToken(token);

        TeamEO teamEO = ModelMapperUtil.getStrictModelMapper().map(teamRequest, TeamEO.class);

        //队长
        teamEO.setUserEO(userService.findByOpenid(openid));


        //等级
        teamEO.setLevel("0");
        log.info("[openid]开始创建团队", openid);
        teamService.addTeam(teamEO);
        log.info("[openid]成功创建团队", openid);
        return new BaseResponse();

    }

    /**
     * @param token
     * @param id
     * @return
     */

    @GetMapping("get/{id}")
    public OneResponse add(@RequestHeader("token") String token, @PathVariable("id") String id) {

        String openid = TokenUtil.getUserOpenidByToken(token);

        TeamEO teamEO = teamService.getById(id);


        TeamVO teamVO = ModelMapperUtil.getStrictModelMapper().map(teamEO, TeamVO.class);
        return successOne(teamVO);
    }

    /**
     * 查找所有团队
     */
    @GetMapping("list")
    public MultiResponse list(@RequestHeader("token") String token) {
        String openid = TokenUtil.getUserOpenidByToken(token);
        List<TeamEO> teamEOS = teamService.list();
        // List<TeamUserEO> teamUserEOS = teamUserService.countTeamUserEOSByTeamEO()
        List<TeamVO> teamVOS = ModelMapperUtil.getStrictModelMapper().map(teamEOS, new TypeToken<List<TeamVO>>() {
        }.getType());
        System.out.println("查看所有的队伍"+teamEOS.toString());
        //每个团队对应人数
        if (teamVOS.size() > 0) {
            for (TeamVO tv : teamVOS
            ) {


                for (TeamEO te : teamEOS
                ) {
                    if (tv.getId().equals(te.getId())) {
                        tv.setPeopleCount(teamUserService.countTeamUserEOSByTeamEO(te).size());
                    }
                }

            }
        }


        return successMulti(teamVOS);
    }


    /**
     * 查询当前用户管理的团队
     */
        @GetMapping("listMyTeam")
    public MultiResponse listMyTeam(@RequestHeader("token") String token) {
        String openid = TokenUtil.getUserOpenidByToken(token);

        UserEO u = userService.findByOpenid(openid);

        List<TeamEO> teamEOS = teamService.findByLeader(u);


        List<TeamVO> teamVOS = ModelMapperUtil.getStrictModelMapper().map(teamEOS, new TypeToken<List<TeamEO>>() {
        }.getType());


        return successMulti(teamVOS);
    }
        @GetMapping("aboutMineTeam")
    public MultiResponse<TeamVO> findAllJoinTeam(@RequestHeader("token") String token){
        String openid = TokenUtil.getUserOpenidByToken(token);

        UserEO u = userService.findByOpenid(openid);
        List<TeamEO> teamEOS = teamService.findAllByUserEO(u);
        List<TeamVO> teamVOS = ModelMapperUtil.getStrictModelMapper().map(teamEOS, new TypeToken<List<TeamEO>>() {
        }.getType());
        return successMulti(teamVOS);
    }

    /**
     * 查询热门团队
     */
    @GetMapping("getHotTeam")
    public MultiResponse getHotTeam(@RequestHeader("token") String token) {
        //String openid = TokenUtil.getUserOpenidByToken(token);
        //List<TeamEO> teamEOS = teamService.getByLevel("4");
        List<TeamVO> teamVOS = teamVoService.findHotTeams();
       // List<TeamVO> teamVOS = ModelMapperUtil.getStrictModelMapper().map(teamEOS, new TypeToken<List<TeamEO>>() {
       // }.getType());
        return successMulti(teamVOS);
    }





    /***
     *  查询当前团队详情和相关任务详情
     * @param id 当前团队的ID
     * @return
     */
    @GetMapping("getInfo/{id}")
    public OneResponse<TeamVO> findByTeamId(@PathVariable("id") String id) {
        //根据团队Id查找团队接受的任务
        TeamVO teamVO = teamVoService.findByTeamId(id);
        System.out.println("你是瓜皮嘛"+teamVO);
        List<MissionVO> missionVOS = missionVoService.findAllByTeamId(id);
        if (teamVO != null) {
            teamVO.setMissionVOS(missionVOS);
        }

        return successOne(teamVO);
    }

}
