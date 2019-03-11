package com.bth.lht.rest.userController;

import com.bth.lht.entity.project.ProjectRewardInfoEO;
import com.bth.lht.entity.user.UserEO;
import com.bth.lht.entity.user.UserInfoEO;
import com.bth.lht.request.user.UserInfoRequest;
import com.bth.lht.request.user.UserRequest;
import com.bth.lht.respose.base.BaseResponse;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.project.ProjectRewardInfoService;
import com.bth.lht.service.user.UserInfoService;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.ModelMapperUtil;
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
@RestController("user")
@RequestMapping
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ProjectRewardInfoService projectRewardInfoService;

    @PostMapping("save")
    public OneResponse save(@Validated @RequestBody UserRequest userRequest){
        UserEO userEO = ModelMapperUtil.getStrictModelMapper().map(userRequest,UserEO.class);
        return successOne(userService.save(userEO));
    }

    @PutMapping("bindInfo/{id}")
    public OneResponse update(@PathVariable(name = "id")String id, @RequestBody @Validated UserInfoRequest userInfoRequest){
        System.out.println(id+"  ---  "+userInfoRequest.toString());
        UserEO userEO = userService.findById(id);
        UserInfoEO userInfoEO = ModelMapperUtil.getStrictModelMapper().map(userInfoRequest,UserInfoEO.class);
        userEO.setUserInfoEO(userInfoService.findById(userInfoService.save(userInfoEO).getId()));
        UserEO res = userService.save(userEO);
        return successOne(res);
    }



    //测试

    @PostMapping("reward")
    public BaseResponse reward(@Validated @RequestBody ProjectRewardInfoEO projectRewardInfoEO){
        projectRewardInfoService.save(projectRewardInfoEO);
        return new BaseResponse();
    }
}
