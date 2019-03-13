package com.bth.lht.dto;

import lombok.Data;

/**
 * @program: lht
 * @description: 聊天消息实体
 * @author: Antony
 * @create: 2019-03-13 14:33
 **/
@Data
public class ChatMessage {
    private String openid;
    private String message;

}
