package com.bth.lht.rest;

import com.bth.lht.respose.base.OneResponse;
import com.bth.lht.util.SendSMS;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Api("手机验证")
@RestController("checkPhone")
public class CheckPhone {
            @GetMapping("/getPHoneCode")
        public String toSendCode( @RequestHeader("token") String token){


           return SendSMS.check();

        }


}
