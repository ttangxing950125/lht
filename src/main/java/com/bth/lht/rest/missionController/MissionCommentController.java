package com.bth.lht.rest.missionController;

import com.bth.lht.entity.project.MissionCommentEO;
import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.mission.MissionCommentRequest;
import com.bth.lht.respose.base.BaseResponse;
import com.bth.lht.respose.base.MultiResponse;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.respose.mission.MissionCommentVO;
import com.bth.lht.respose.wxUser.UserVO;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.project.MissionCommentService;
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

import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private UserService userService;
    @ApiOperation("查找任务的所有评论")
    @GetMapping("listByMissionComments/{missionId}")
    public MultiResponse list(@PathVariable("missionId")String missionId,@RequestHeader("token")String token){
        String openid = TokenUtil.getUserOpenidByToken(token);

        List<MissionCommentEO> commentEOS = missionCommentService.findMissionCommentEOSByMissionsEO(missionsService.get(missionId));
        List<MissionCommentVO> commentVOS = ModelMapperUtil.getStrictModelMapper().map(commentEOS,new TypeToken<List<MissionCommentVO>>(){}.getType());
        SimpleDateFormat sf =new SimpleDateFormat("yyyy年MM月dd日");
        UserVO userVO = null;
      for (int i=0;i<commentEOS.size();i++){
          userVO = ModelMapperUtil.getStrictModelMapper().map(userService.findByOpenid(commentEOS.get(i).getOpenid()),UserVO.class);
          commentVOS.get(i).setCreatTime(sf.format(commentEOS.get(i).getCreateDate()));
          commentVOS.get(i).setUserVO(userVO);

      }

        return successMulti(commentVOS);
    }

    @ApiOperation("添加评论")
    @PostMapping("saveComment")
    public OneResponse save(@RequestBody @Validated MissionCommentRequest request,@RequestHeader("token")String token){
        String openid = TokenUtil.getUserOpenidByToken(token);
        MissionCommentEO commentEO = new MissionCommentEO();
        Date date =new Date();

        commentEO.setOpenid(openid);
        commentEO.setContent(request.getContent());
        System.out.println("you you you "+request);
        commentEO.setMissionsEO(missionsService.get(request.getMissionId()));
        commentEO.setCreateDate(date);

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

        @PostMapping("addMissionComment")
        public OneResponse addMissionComments(@RequestHeader("token")String token,@RequestBody @Validated MissionCommentRequest mcomment){
            String openid = TokenUtil.getUserOpenidByToken(token);
            UserEO userEO = userService.findByOpenid(openid);
            Date date =new Date();
            //
            MissionCommentEO missionCommentEO =new MissionCommentEO();
            if (mcomment!=null){
                missionCommentEO.setCreateDate(date);
                missionCommentEO.setContent(mcomment.getContent());
                //找到当前任务
                MissionsEO missionsEO = missionsService.get(mcomment.getMissionId());

                missionCommentEO.setMissionsEO(missionsEO);
                missionCommentEO.setOpenid(openid);

            }
            String dec = null;
            try {
               missionCommentService.save(missionCommentEO);
               dec = "评论发表成功！";
            }catch (Exception e){
                e.printStackTrace();

                dec =  "评论失败！";
            }
           UserVO userVO = ModelMapperUtil.getStrictModelMapper().map(userEO,UserVO.class);

            SimpleDateFormat sf =new SimpleDateFormat("yyyy年MM月dd日");
               OneResponse oneResponse =new OneResponse();
                MissionCommentVO missionCommentVO = ModelMapperUtil.getStrictModelMapper().map(missionCommentEO,new TypeToken<MissionCommentVO>(){}.getType());
                    missionCommentVO.setCreatTime(sf.format(date));
                    missionCommentVO.setUserVO(userVO);
                    oneResponse.setDesc(dec);
                    oneResponse.setData(missionCommentVO);
                return successOne(oneResponse);

        }

}
