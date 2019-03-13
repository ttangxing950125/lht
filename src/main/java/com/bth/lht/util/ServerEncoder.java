package com.bth.lht.util;

import com.alibaba.fastjson.JSON;
import com.bth.lht.dto.ChatMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @program: lht
 * @description: 解码器
 * @author: Antony
 * @create: 2019-03-13 14:40
 **/
public class ServerEncoder implements Encoder.Text<ChatMessage> {
    @Override
    public String encode(ChatMessage chatMessage) throws EncodeException {
            return JSON.toJSONString(chatMessage);

    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
