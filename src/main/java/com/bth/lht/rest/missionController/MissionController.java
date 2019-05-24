package com.bth.lht.rest.missionController;

import com.bth.lht.dao.user.UserRepository;
import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.mission.MissionRequest;
import com.bth.lht.respose.base.BaseResponse;
import com.bth.lht.respose.base.MultiResponse;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.respose.mission.IsMineMissionVO;
import com.bth.lht.respose.mission.MissionVO;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.project.MissionUserService;
import com.bth.lht.service.project.MissionsService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.ModelMapperUtil;
import com.bth.lht.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: lht
 * @description: 任务控制器
 * @author: Antony
 * @create: 2019-03-12 09:38
 **/
@RestController
@RequestMapping("/missions")
@Api("任务接口")
@Slf4j
public class MissionController extends BaseController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MissionUserService missionUserService;
    @Autowired
    private MissionsService missionsService;
    @GetMapping("list")
    @ApiOperation("查找所有任务")
    public MultiResponse list(@RequestHeader("token")String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        log.info("用户【{openid}】请求",openid);
        List<MissionsEO> list = missionsService.list();
        List<MissionVO> missionVOList = ModelMapperUtil.getStrictModelMapper().map(list,new TypeToken< List<MissionVO>>(){}.getType());
        return successMulti(missionVOList);
    }
    @GetMapping("listPublish")
    @ApiOperation("查找指定用户发布的任务")
    public MultiResponse listPublish(@RequestHeader("token")String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        List<MissionsEO> list = missionsService.findByLeader(userService.findByOpenid(openid));
        List<MissionVO> missionVOList = ModelMapperUtil.getStrictModelMapper().map(list,new TypeToken< List<MissionVO>>(){}.getType());
        return successMulti(missionVOList);
    }
    /**
     *
     * 添加任务操作，注：为任务添加leaderId
     * @param missionRequest
     * @param token
     * @return
     */
    @PostMapping("add")
    @ApiOperation("添加任务")
    public OneResponse add(@Validated @RequestBody MissionRequest missionRequest,@RequestHeader("token")String token){
        OneResponse oneResponse =new OneResponse();
        String openid = TokenUtil.getUserOpenidByToken(token);
        UserEO userEO = userService.findByOpenid(TokenUtil.getUserOpenidByToken(token));

        MissionsEO missionsEO = ModelMapperUtil.getStrictModelMapper().map(missionRequest,MissionsEO.class);
        missionsEO.setLeaderEO(userRepository.findUserEOByWxOpenid(openid));
        //初始化父级任务
        missionsEO.setParentId("0");
        log.info("开始添加任务");
        if(userEO.getPhoneNumber()!=null){
           MissionsEO m= missionsService.save(missionsEO);
            log.info("结束添加任务");
//            oneResponse.setStatus(1);
//            oneResponse.setDesc("OK");
            return successOne(m);
        }else {
//            oneResponse.setStatus(2);
            return successOne(null);
        }


    }
    /**
     * 拆分任务，注：拆分任务逻辑：重新添加该任务，需要修改其leaderID,并修改其任务等级
     * @return
     */
    @ApiOperation("拆分任务")
    @PostMapping("split/{parentId}")
    public OneResponse split(@Validated @RequestBody MissionRequest missionRequest,@PathVariable("parentId")String parentId,@RequestHeader("token")String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        MissionsEO missionsEO = ModelMapperUtil.getStrictModelMapper().map(missionRequest,MissionsEO.class);
        //设置领导人id
        missionsEO.setLeaderEO(userRepository.findUserEOByWxOpenid(openid));
        //设置父任务id
        missionsEO.setParentId(parentId);
        MissionsEO m = missionsService.save(missionsEO);
        return successOne(m);
    }
    /**
     * 提交任务 注：任务任意字段改变都可称为修改任务
     */
    @PutMapping("update")
    @ApiOperation("更新任务")
    public BaseResponse update(){
        return new BaseResponse();
    }

    /**
     * 判断是不是用户自己发布的任务
     */
    @ApiOperation("判断是不是用户自己的任务")
    @GetMapping("isMineMission/{missionId}")
    public OneResponse isMineMission(@RequestHeader("token")String token,@PathVariable("missionId")String missionId){
        UserEO userEO = userService.findByOpenid(TokenUtil.getUserOpenidByToken(token));
        MissionsEO missionsEO = missionsService.get(missionId);
        IsMineMissionVO isMineMissionVO = new IsMineMissionVO();
        if (missionsEO.getLeaderEO().equals(userEO)){
            isMineMissionVO.setMine(true);
        }
        MissionUserEO missionUserEO = missionUserService.findMissionUserEOByUserEOAndMissionsEO(userEO,missionsEO);
        if (missionUserEO!=null){
            isMineMissionVO.setReceived(true);
        }

        return successOne(isMineMissionVO);
    }

    /**
     * 通过id查找任务
     */
    @ApiOperation("通过id查找任务")
    @GetMapping("get/{id}")
    public OneResponse getById(@PathVariable("id")String id,@RequestHeader("token")String token){
        MissionsEO m = missionsService.get(id);
        MissionVO missionVO = null;
        if(m!=null) {
            missionVO  = ModelMapperUtil.getStrictModelMapper().map(m, MissionVO.class);
        }
        return successOne(missionVO);
    }

    /**
     * 根据当前用户查找接到的任务
     */
    public MultiResponse findMissionsByUserId(@RequestHeader("token")String token){
        UserEO userEO = userService.findByOpenid(TokenUtil.getUserOpenidByToken(token));
        List<MissionUserEO> missionUserEOS = missionUserService.findMissionUserEOSByUserEO(userEO);
        //找到对应的任务体
        List<MissionsEO> missionsEOS =new ArrayList<>();
        for (MissionUserEO mu:missionUserEOS
             ) {
            missionsEOS.add(missionsService.get(mu.getMissionsEO().getId()));

        }
        List<MissionVO> missionVOS = ModelMapperUtil.getStrictModelMapper().map(missionsEOS, new TypeToken<List<MissionVO>>(){}.getType());
        return  successMulti(missionVOS);
    }

}
