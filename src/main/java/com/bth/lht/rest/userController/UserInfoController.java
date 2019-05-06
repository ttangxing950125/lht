package com.bth.lht.rest.userController;

import com.bth.lht.entity.team.TeamEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.entity.user.UserInfoEO;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.respose.team.TeamUserVO;
import com.bth.lht.respose.team.TeamVO;
import com.bth.lht.respose.wxUser.UserInfoVO;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.team.TeamService;
import com.bth.lht.service.user.UserInfoService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.ModelMapperUtil;
import com.bth.lht.util.TokenUtil;
import io.swagger.annotations.Api;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: lht
 * @description: controller-用户信息
 * @author: Antony
 * @create: 2018-12-21 14:14
 **/
@Api(tags = "用户信息接口")
@RestController
@RequestMapping("userInfo")
public class UserInfoController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;

    @PostMapping("save")
    public OneResponse<UserInfoEO> save(@Validated @RequestBody UserInfoEO userInfoEO){



        return  successOne(userInfoService.save(userInfoEO));
    }

    public OneResponse<UserInfoVO> findUserInfoByTeamId(@RequestHeader("token")String token){
        //获取当前用户信息
        String openid = TokenUtil.getUserOpenidByToken(token);
        UserEO userEO = userService.findByOpenid(openid);
        //根据当前用户查找团队
        List<TeamEO> teamEOS = teamService.findByLeader(userEO);
        List<TeamVO> teamVOS = ModelMapperUtil.getStrictModelMapper().map(teamEOS,new TypeToken<List<TeamVO>>(){}.getType());



        return  null;
    }


}
