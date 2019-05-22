package com.bth.lht.rest;

import com.bth.lht.entity.user.UserEO;
import com.bth.lht.request.phoneCode.PhoneCode;
import com.bth.lht.respose.base.OneResponse;


import com.bth.lht.respose.wxUser.UserVO;
import com.bth.lht.service.user.UserService;
import com.bth.lht.util.ModelMapperUtil;
import com.bth.lht.util.SmsCodeCkeck;
import com.bth.lht.util.TokenUtil;
import io.swagger.annotations.Api;
import com.bth.lht.rest.baseController.BaseController;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Api("发送短信")
@RestController
@RequestMapping("/sendSms")
public class SendSms extends BaseController{
    @Autowired
    private UserService userService;

    @PostMapping("sendPhoneCode/{phoneNUM}")
    public OneResponse<String> sendSms(@RequestHeader("token")String token,@PathVariable("phoneNUM")String phoneNUM){
        String openid = TokenUtil.getUserOpenidByToken(token);
        UserEO userEO = userService.findByOpenid(openid);

        OneResponse oneResponse =new OneResponse();
        //生成随机码
        String code = new SmsCodeCkeck().getRadomNum();
        //将验证码保存起来
        if (userEO!=null){
            userEO.setPhoneCode(code);
            userService.save(userEO);
        }


        String  message = SmsCodeCkeck.getPhoneCode(phoneNUM,code);
                oneResponse.setData(message);
        return successOne(oneResponse);
    }

    //添加手机号
    @PostMapping("cheeckPhone")
    public OneResponse<UserEO> checkPhoneCode(@RequestHeader("token")String token, @Validated @RequestBody PhoneCode phoneCode){
        String openid = TokenUtil.getUserOpenidByToken(token);
        UserEO userEO = userService.findByOpenid(openid);
       //验证验证码是否正确
        System.out.println("验证码是"+phoneCode);
        if (phoneCode!=null && userEO!=null){
            if (phoneCode.getPhoneCode().equals(userEO.getPhoneCode())){
                userEO.setPhoneNumber(phoneCode.getPhoneNum());
                userService.save(userEO);
            }
        }

        UserVO userVO = ModelMapperUtil.getStrictModelMapper().map(userEO,new TypeToken<UserVO>(){}.getType());
            return successOne(userVO);

    }



}
