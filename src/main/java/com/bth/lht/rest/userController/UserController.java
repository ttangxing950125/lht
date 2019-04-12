package com.bth.lht.rest.userController;

import com.bth.lht.entity.project.ProjectRewardInfoEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.entity.user.UserInfoEO;
import com.bth.lht.request.user.LoginRequest;
import com.bth.lht.request.user.UserInfoRequest;
import com.bth.lht.request.user.UserRequest;
import com.bth.lht.respose.TokenVO;
import com.bth.lht.respose.base.BaseResponse;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.respose.wxUser.WxUserKeyVO;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.WxAppletService;
import com.bth.lht.service.project.ProjectRewardInfoService;
import com.bth.lht.service.user.UserInfoService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.ModelMapperUtil;
import com.bth.lht.util.TokenUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: lht
 * @description: controller-用户
 * @author: Antony
 * @create: 2018-12-21 18:17
 **/
@Api("用户接口")
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController {
    @Autowired
    private WxAppletService wxAppletService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ProjectRewardInfoService projectRewardInfoService;







    @PostMapping("/login")
    public OneResponse<TokenVO> login(@Validated @RequestBody LoginRequest loginRequest){
        TokenVO tokenVO = new TokenVO();
        System.out.println(loginRequest);
        System.out.println(successOne(tokenVO).getDesc()+"123456");
        //返回token
        String token = userService.save(loginRequest);
        tokenVO.setToken(token);
        tokenVO.setOpenid(TokenUtil.getUserOpenidByToken(token));

        return successOne(tokenVO);
    }



    //测试

    @PostMapping("reward")
    public OneResponse reward(){
        return successOne(new StringBuffer("123123"));
    }
}
