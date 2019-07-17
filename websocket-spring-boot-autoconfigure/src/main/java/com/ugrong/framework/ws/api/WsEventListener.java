package com.ugrong.framework.ws.api;

import com.ugrong.framework.ws.config.WsProperties;
import com.ugrong.framework.ws.model.StandardMessageType;
import com.ugrong.framework.ws.model.WsEventType;
import com.ugrong.framework.ws.model.WsMessage;
import com.ugrong.framework.ws.service.WsRouteService;
import com.ugrong.framework.ws.utils.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Date;

@Slf4j
@AllArgsConstructor
public class WsEventListener {

    private final WsProperties wsProperties;

    private final WsRouteService wsRouteService;

    @EventListener
    public void handleWsConnected(SessionConnectedEvent event) {
        String content = "Received a new web socket connection.";

        String userId = event.getUser().getName();
        log.info(content.concat("user=[{}]"), userId);

        this.wsRouteService.handleMessage(buildWsMessage(userId, content, WsEventType.CONNECTED));
    }

    @EventListener
    public void handleWsDisconnect(SessionDisconnectEvent event) {
        String content = "User Disconnected.";
        String userId = event.getUser().getName();
        log.info(content.concat("user=[{}], [sessionId=[{}]"), userId, event.getSessionId());

        this.wsRouteService.handleMessage(buildWsMessage(userId, content, WsEventType.DISCONNECT));
    }

    private WsMessage buildWsMessage(String userId, String content, WsEventType eventType) {
        WsMessage message = new WsMessage();
        message.setSendTime(new Date());
        message.setMessageId(RandomUtil.generateUUID());
        message.setMsgType(StandardMessageType.EVENT.name());
        message.setSender(this.wsProperties.getSystemUserId());
        message.setReceiver(userId);
        message.setEventType(eventType);
        message.setContent(content);
        return message;
    }
}
