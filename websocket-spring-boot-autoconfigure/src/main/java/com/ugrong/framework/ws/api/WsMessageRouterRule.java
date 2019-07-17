package com.ugrong.framework.ws.api;

import com.ugrong.framework.ws.model.WsEventType;
import com.ugrong.framework.ws.model.WsException;
import com.ugrong.framework.ws.model.WsMessage;
import com.ugrong.framework.ws.service.WsMessageService;
import com.ugrong.framework.ws.utils.WsValidator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Getter
@Setter
public class WsMessageRouterRule {

    private final WsMessageRouter routerBuilder;

    private WsMessageType msgType;

    private WsEventType eventType;

    private String sender;

    private boolean reEnter = false;

    private List<WsMessageHandler> handlers = new ArrayList<>();

    private WsExceptionHandler exceptionHandler;

    public WsMessageRouterRule(WsMessageRouter routerBuilder) {
        WsValidator.notNull(routerBuilder, "消息路由器不能为空");
        this.routerBuilder = routerBuilder;
    }

    /**
     * 如果消息发送者等于某值
     */
    public WsMessageRouterRule sender(String sender) {
        WsValidator.notBlank(sender, "发送者不能为空");
        this.sender = sender;
        return this;
    }

    /**
     * 如果msgType等于某值
     */
    public WsMessageRouterRule msgType(WsMessageType msgType) {
        WsValidator.isTrue(msgType != null && StringUtils.isNotBlank(msgType.getType()),
                "消息类型不能为空");
        this.msgType = msgType;
        return this;
    }

    /**
     * 如果eventType等于某值
     */
    public WsMessageRouterRule event(WsEventType eventType) {
        WsValidator.notNull(eventType, "事件类型不能为空");
        this.eventType = eventType;
        return this;
    }

    /**
     * 设置websocket消息处理器
     */
    public WsMessageRouterRule handler(WsMessageHandler handler) {
        return this.handler(handler, (WsMessageHandler[]) null);
    }

    /**
     * 设置websocket消息处理器
     */
    public WsMessageRouterRule handler(WsMessageHandler handler, WsMessageHandler... otherHandlers) {
        WsValidator.notNull(handler, "消息处理器不能为空");
        this.handlers.add(handler);

        Optional.ofNullable(otherHandlers)
                .filter(ArrayUtils::isNotEmpty)
                .ifPresent(otherWsHandlers -> handlers.addAll(Arrays.asList(otherWsHandlers)));

        return this;
    }

    /**
     * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则
     */
    public WsMessageRouter end() {
        this.routerBuilder.getRules().add(this);
        return this.routerBuilder;
    }

    /**
     * 规则结束，但是消息还会进入其他规则
     */
    public WsMessageRouter next() {
        this.reEnter = true;
        return this.end();
    }

    /**
     * 匹配规则
     */
    protected boolean test(WsMessage message) {
        return (this.sender == null || this.sender.equals(message.getSender())) &&
                (this.msgType == null || this.msgType.getType().equals(message.getMsgType())) &&
                (this.eventType == null || this.eventType == message.getEventType());
    }

    protected void service(WsMessage message, WsMessageService wsMessageService, WsExceptionHandler exceptionHandler) {
        try {
            // 交给handler处理
            this.handlers.stream()
                    .filter(Objects::nonNull)
                    .forEach(handler -> handler.handle(message, wsMessageService));
        } catch (WsException e) {
            exceptionHandler.handle(e);
        }
    }
}
