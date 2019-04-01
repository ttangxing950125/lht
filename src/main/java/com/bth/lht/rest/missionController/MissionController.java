package com.bth.lht.rest.missionController;

import com.bth.lht.dao.project.MissionsRepository;
import com.bth.lht.dao.user.UserRepository;
import com.bth.lht.entity.project.MissionUserEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.mission.MissionRequest;
import com.bth.lht.respose.base.BaseResponse;
import com.bth.lht.respose.base.MultiResponse;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.respose.mission.MissionVO;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.project.MissionUserService;
import com.bth.lht.service.project.MissionsService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.ModelMapperUtil;
import com.bth.lht.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.TokenType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.awt.*;
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
    private MissionsRepository missionsRepository;
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

    /**
     *
     * 添加任务操作，注：为任务添加leaderId
     * @param missionRequest
     * @param token
     * @return
     */
    @PutMapping("add")
    @ApiOperation("添加任务")
    public OneResponse<MissionsEO> add(@Validated @RequestBody MissionRequest missionRequest,@RequestHeader("token")String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        MissionsEO missionsEO = ModelMapperUtil.getStrictModelMapper().map(missionRequest,MissionsEO.class);
        missionsEO.setLeaderEO(userRepository.findByWxOpenid(openid));
        log.info("开始添加任务");
        missionsService.save(missionsEO);
        log.info("结束添加任务");
        return successOne(missionsEO);
    }

    /**
     * 接受任务，重新添加任务，但leaderID不能修改，为任务加上用户openid或者团队id 多对多
     * @return
     */
    @PutMapping("join/{id}")
    @ApiOperation("接受任务")
    public BaseResponse join(@PathVariable("id")String id,@RequestHeader("token")String token){
        MissionUserEO missionUserEO = new MissionUserEO();
        String openid = TokenUtil.getUserOpenidByToken(token);

        //得到该用户
        UserEO userEO = userRepository.findByWxOpenid(openid);
        //接收请求对象，并映射为实体
        MissionsEO missionsEO = missionsService.get(id);
        if (missionsEO!=null) {
            //接收任务的团队或者个人
            missionUserEO.setStatus("doing");
            missionUserEO.setMissionsEO(missionsEO);
            missionUserEO.setUserEO(userEO);
            missionUserService.save(missionUserEO);
        }else {
            System.out.println("没找到任务");
        }
        return new BaseResponse();
    }

    /**
     * 拆分任务，注：拆分任务逻辑：重新添加该任务，需要修改其leaderID,并修改其任务等级
     * @return
     */

    @ApiOperation("拆分任务")
    @PostMapping("split")
    public BaseResponse split(){
        return new BaseResponse();
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
     * 按类别查找查找任务 进行中 待审核 已完成 0 1 2
     */
    @GetMapping("doing/{category}")
    @ApiOperation("查找进行中的任务")
    public BaseResponse listDoing(@RequestHeader("token")String token,@PathVariable("category")String category){
        String openid = TokenUtil.getUserOpenidByToken(token);
        System.out.println(category+"   "+token);
        //通过类别查找   0  进行中 1 待审核 2 已完成 3其他

        return new BaseResponse();
    }
}
