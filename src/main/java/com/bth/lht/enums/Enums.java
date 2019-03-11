package com.bth.lht.enums;

/**
 * @program: lht
 * @description: 枚举类
 * @author: Antony
 * @create: 2018-12-21 12:44
 **/
public class Enums {
    public enum VerificationCodeType{
        //任务验证码
        MISSION,
        //团队验证码
        TEAM,
    }
    public enum VerificationCodeStatus{
        //无效
        INVALID,
        //有效
        VALID
    }
    public enum ProjectRewardType{
        //人民币
        CNY,
        //LHT
        LHT
    }
}
