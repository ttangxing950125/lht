package com.bth.lht.respose.mission;

import lombok.Data;

/**
 * @program: lht
 * @description: 判断是否是自己发布的任务
 * @author: Antony
 * @create: 2019-04-10 20:03
 **/
@Data
public class IsMineMissionVO {
    private boolean isMine;
    private boolean isReceived;
}
