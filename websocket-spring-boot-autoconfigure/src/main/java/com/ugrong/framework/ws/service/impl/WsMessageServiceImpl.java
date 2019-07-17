package com.ugrong.framework.ws.service.impl;

import com.ugrong.framework.ws.config.WsProperties;
import com.ugrong.framework.ws.service.WsMessageService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@AllArgsConstructor
public class WsMessageServiceImpl implements WsMessageService {

    private final WsProperties wsProperties;

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void broadcast(Object payload) {
        this.messagingTemplate.convertAndSend(wsProperties.getPublicTopicPrefix().concat(wsProperties.getPublicTopicSuffix())
                , payload);
    }

    @Override
    public void sendToUser(String userId, Object payload) {
        this.messagingTemplate.convertAndSendToUser(userId, wsProperties.getUserTopicSuffix(), payload);
    }
}
