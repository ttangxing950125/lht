package com.bth.lht.service;

import com.bth.lht.request.user.LoginRequest;
import com.bth.lht.respose.wxUser.WxUserKeyVO;

/**
 * @program: lht
 * @description:
 * @author: Antony
 * @create: 2019-03-19 11:25
 **/
public interface WxAppletService {
    WxUserKeyVO getSessionKeyOrOpenid(String code);

}
