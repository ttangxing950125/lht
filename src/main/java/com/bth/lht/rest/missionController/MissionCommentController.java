package com.bth.lht.rest.missionController;

import com.bth.lht.entity.project.MissionCommentEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.request.mission.MissionCommentRequest;
import com.bth.lht.respose.base.BaseResponse;
import com.bth.lht.respose.base.MultiResponse;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.respose.mission.MissionCommentVO;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.project.MissionCommentService;
import com.bth.lht.service.project.MissionsService;
import com.bth.lht.util.ModelMapperUtil;
import com.bth.lht.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: lht
 * @description: 任务评论控制器
 * @author: Antony
 * @create: 2019-04-01 18:51
 **/
@Api("任务评论接口")
@RestController
@Slf4j
@RequestMapping("missionComment")
public class MissionCommentController extends BaseController {

    @Autowired
    private MissionCommentService missionCommentService;
    @Autowired
    private MissionsService missionsService;
    @ApiOperation("查找任务的所有评论")
    @GetMapping("listByMission/{missionId}")
    public MultiResponse list(@PathVariable("missionId")String missionId){
        List<MissionCommentEO> commentEOS = missionCommentService.findMissionCommentEOSByMissionsEO(missionsService.get(missionId));
        List<MissionCommentVO> commentVOS = ModelMapperUtil.getStrictModelMapper().map(commentEOS,new TypeToken<List<MissionCommentVO>>(){}.getType());
            return successMulti(commentVOS);
    }

    @ApiOperation("添加评论")
    @PostMapping("saveComment")
    public OneResponse save(@RequestBody @Validated MissionCommentRequest request,@RequestHeader("token")String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        MissionCommentEO commentEO = new MissionCommentEO();
        commentEO.setOpenid(openid);
        commentEO.setContent(request.getContent());
        System.out.println("you you you "+request);
        commentEO.setMissionsEO(missionsService.get(request.getMissionId()));


       MissionCommentEO res =  missionCommentService.save(commentEO);
//       响应封装
        MissionCommentVO commentVO = new MissionCommentVO();
        commentVO.setContent(res.getContent());

        //开始操作
        return successOne(commentVO);
    }

    @ApiOperation("删除评论")
    @DeleteMapping("deleteComment/{id}")
    public BaseResponse delete(@PathVariable("id")String id){
        missionCommentService.delete(id);
        return new BaseResponse();
    }

}
