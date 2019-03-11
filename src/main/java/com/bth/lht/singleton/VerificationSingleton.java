package com.bth.lht.singleton;

import com.bth.lht.enums.Enums;
import lombok.Data;

/**
 * @program: lht
 * @description: 验证码单例
 * @author: Antony
 * @create: 2018-12-21 12:43
 **/
@Data
public class VerificationSingleton {
    private static volatile VerificationSingleton singleton;
    private String phoneNumber;
    private String code;
    private Enums.VerificationCodeType verificationCodeType;
    private String time;
    private Enums.VerificationCodeStatus verificationCodeStatus;


    private VerificationSingleton() {}

    public static VerificationSingleton getInstance() {
        if (singleton == null) {
            synchronized (VerificationSingleton.class) {
                if (singleton == null) {
                    singleton = new VerificationSingleton();
                }
            }
        }
        return singleton;
    }
}
