package com.bth.lht.respose.wxUser;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class UserVO {
    private String id;
    private String wxOpenid;
    private String wxNickName;
    private String avatarUrl;
    private BigDecimal integral;
    private String phoneNumber;
}
