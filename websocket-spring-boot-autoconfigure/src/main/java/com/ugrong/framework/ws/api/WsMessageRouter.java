package com.ugrong.framework.ws.api;

import com.ugrong.framework.ws.handler.LogExceptionHandler;
import com.ugrong.framework.ws.model.WsMessage;
import com.ugrong.framework.ws.service.WsMessageService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * websocket消息路由器
 */
@Getter
@Setter
public class WsMessageRouter {

    private final List<WsMessageRouterRule> rules = new ArrayList<>();

    private WsExceptionHandler exceptionHandler;

    public WsMessageRouter() {
        this.exceptionHandler = new LogExceptionHandler();
    }

    /**
     * 开始一个新的Route规则
     */
    public WsMessageRouterRule rule() {
        return new WsMessageRouterRule(this);
    }

    /**
     * 处理websocket消息
     */
    public void route(final WsMessage message, final WsMessageService wsMessageService) {
        //执行service
        List<WsMessageRouterRule> rules = matchRule(message);
        if (!rules.isEmpty()) {
            rules.stream().forEach(rule -> rule.service(message, wsMessageService, this.exceptionHandler));
        }
    }

    private List<WsMessageRouterRule> matchRule(final WsMessage message) {
        final List<WsMessageRouterRule> matchRules = new ArrayList<>();

        // 收集匹配的规则
        for (final WsMessageRouterRule rule : this.rules) {
            if (rule.test(message)) {
                matchRules.add(rule);
                if (!rule.isReEnter()) {
                    break;
                }
            }
        }
        return matchRules;
    }
}
