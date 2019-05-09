package com.bth.lht.rest.comments;

import com.bth.lht.entity.project.MissionsEO;
import com.bth.lht.entity.project.UserComments;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.UserCommentRequest.UserCommentRequest;
import com.bth.lht.respose.UserCommentResponse;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.respose.team.TeamVO;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.project.MissionsService;
import com.bth.lht.service.project.UserCommentService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.ModelMapperUtil;
import com.bth.lht.util.TokenUtil;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("userComments")

public class UserCommentController extends BaseController {

    @Autowired
    private UserCommentService userCommentService;
    @Autowired
    private UserService userService;
    @Autowired
    private MissionsService missionsService;

    @PostMapping("addComment")
    public OneResponse<UserCommentResponse> addNew(@RequestHeader("token") String token, @RequestBody @Validated UserCommentRequest userCommentRequest){
        //@RequestHeader("token") String token
        String openid = TokenUtil.getUserOpenidByToken(token);
        UserEO userEO = userService.findByOpenid(openid);
        //评论响应实体
        UserComments userComments = new UserComments();

        if (userCommentRequest!=null){

            //找出完成任务的用户
            UserEO userEO1 = userService.findById(userCommentRequest.getUserid());
            MissionsEO missionsEO = missionsService.get(userCommentRequest.getCommentMissionid());
            UserComments  userComments1= userCommentService.findByAndCommentUserAndUserEO(userEO,userEO1);

            if (userComments1!=null){
                userComments1.setCommemtText(userCommentRequest.getCommentText());
                userComments1.setCommentLevel(userCommentRequest.getConnmentLevel());
                userCommentService.save(userComments1);
                UserCommentResponse userCommentResponse = ModelMapperUtil.getStrictModelMapper().map(userComments1, new TypeToken<UserCommentResponse>() {
                }.getType());
                return successOne(userCommentResponse);
            }else {
                userComments.setCommemtText(userCommentRequest.getCommentText());
                userComments.setCommentLevel(userCommentRequest.getConnmentLevel());
            userComments.setMissionsEO(missionsEO);
            userComments.setUserEO(userEO1);

            userComments.setCommentUser(userEO);
            userComments.setStatus("done");
            userComments.setCreateDate(new Date());
            userCommentService.save(userComments);
        }
        }

        UserCommentResponse userCommentResponse = ModelMapperUtil.getStrictModelMapper().map(userComments, new TypeToken<UserCommentResponse>() {
        }.getType());

        return successOne(userCommentResponse);
    }


}
