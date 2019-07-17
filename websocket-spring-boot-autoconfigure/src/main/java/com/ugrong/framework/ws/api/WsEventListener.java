package com.ugrong.framework.ws.api;

import com.ugrong.framework.ws.model.StandardMessageType;
import com.ugrong.framework.ws.model.WsEventType;
import com.ugrong.framework.ws.model.WsMessage;
import com.ugrong.framework.ws.service.WsRouteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Date;

@Slf4j
@AllArgsConstructor
public class WsEventListener {

    private final WsRouteService wsRouteService;

    @EventListener
    public void handleWsConnected(SessionConnectedEvent event) {
        String content = "Received a new web socket connection.";
        log.info(content.concat("user=[{}]"), event.getUser());

        WsMessage message = buildWsMessage();
        message.setEventType(WsEventType.CONNECTED);
        message.setContent(content);

        this.wsRouteService.handleMessage(message);
    }

    @EventListener
    public void handleWsDisconnect(SessionDisconnectEvent event) {
        String content = "User Disconnected.";
        log.info(content.concat("user=[{}], [sessionId=[{}]"), event.getUser(), event.getSessionId());

        WsMessage message = buildWsMessage();
        message.setEventType(WsEventType.DISCONNECT);
        message.setContent(content);

        this.wsRouteService.handleMessage(message);
    }

    private WsMessage buildWsMessage() {
        WsMessage message = new WsMessage();
        message.setSendTime(new Date());
        message.setMsgType(StandardMessageType.EVENT.name());
        return message;
    }
}
