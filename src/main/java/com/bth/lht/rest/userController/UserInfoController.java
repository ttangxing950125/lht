package com.bth.lht.rest.userController;

import com.bth.lht.entity.user.UserInfoEO;
import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.rest.baseController.BaseController;
import com.bth.lht.service.user.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("save")
    public OneResponse<UserInfoEO> save(@Validated @RequestBody UserInfoEO userInfoEO){
        System.out.println(userInfoEO.getCreateDate());

       UserInfoEO u = userInfoService.save(userInfoEO);
        return  successOne(u);
    }
}
