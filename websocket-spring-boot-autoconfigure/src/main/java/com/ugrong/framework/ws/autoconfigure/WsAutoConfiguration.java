package com.ugrong.framework.ws.autoconfigure;

import com.ugrong.framework.ws.api.WsMessageRouter;
import com.ugrong.framework.ws.config.WsMessageBrokerConfig;
import com.ugrong.framework.ws.config.WsProperties;
import com.ugrong.framework.ws.interceptor.DefaultWsAuthInterceptor;
import com.ugrong.framework.ws.interceptor.DefaultWsHandshakeHandler;
import com.ugrong.framework.ws.interceptor.WsAuthInterceptor;
import com.ugrong.framework.ws.interceptor.WsHandshakeHandler;
import com.ugrong.framework.ws.service.WsMessageService;
import com.ugrong.framework.ws.service.WsRouteService;
import com.ugrong.framework.ws.service.impl.WsMessageServiceImpl;
import com.ugrong.framework.ws.service.impl.WsRouteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Configuration
@ConditionalOnClass({MessageSendingOperations.class})
@EnableConfigurationProperties(WsProperties.class)
@EnableWebSocketMessageBroker
@AutoConfigureAfter(WebSocketMessagingAutoConfiguration.class)
@ComponentScan(basePackages = {"com.ugrong.framework.ws.controller"})
@AllArgsConstructor
public class WsAutoConfiguration {

    private final WsProperties wsProperties;

    @Bean
    @ConditionalOnMissingBean
    public WsProperties wsProperties() {
        return this.wsProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(WsProperties.class)
    public WsAuthInterceptor wsAuthInterceptor(WsProperties wsProperties) {
        return new DefaultWsAuthInterceptor(wsProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(WsProperties.class)
    public WsHandshakeHandler wsHandshakeHandler(WsProperties wsProperties) {
        return new DefaultWsHandshakeHandler(wsProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean({WsProperties.class, WsHandshakeHandler.class, WsAuthInterceptor.class})
    public WsMessageBrokerConfig wsMessageBrokerConfig(WsProperties wsProperties, WsAuthInterceptor interceptor, WsHandshakeHandler handler) {
        return new WsMessageBrokerConfig(wsProperties, interceptor, handler);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean({WsMessageBrokerConfig.class, SimpMessagingTemplate.class})
    public WsMessageService wsMessageService(WsProperties wsProperties, SimpMessagingTemplate messagingTemplate) {
        return new WsMessageServiceImpl(wsProperties, messagingTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public WsMessageRouter wsMessageRouter() {
        return new WsMessageRouter();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean({WsMessageRouter.class, WsMessageService.class})
    public WsRouteService wsRouteService(WsMessageRouter wsMessageRouter, WsMessageService wsMessageService) {
        return new WsRouteServiceImpl(wsMessageRouter, wsMessageService);
    }

//    @Bean
//    @ConditionalOnMissingBean
//    @ConditionalOnBean(WsRouteService.class)
//    public WsMessageController wsMessageController(WsRouteService wsRouteService) {
//        return new WsMessageControllerImpl(wsRouteService);
//    }
}