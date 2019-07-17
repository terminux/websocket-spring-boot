package com.ugrong.framework.ws.config;

import com.ugrong.framework.ws.interceptor.WsAuthInterceptor;
import com.ugrong.framework.ws.interceptor.WsHandshakeHandler;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@AllArgsConstructor
public class WsMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

    private final WsProperties wsProperties;

    private final WsAuthInterceptor wsAuthInterceptor;

    private final WsHandshakeHandler wsHandshakeHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(wsProperties.getEndpoint())
                .addInterceptors(wsAuthInterceptor)
                .setHandshakeHandler(wsHandshakeHandler)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(wsProperties.getMessagePrefix());
        registry.setUserDestinationPrefix(wsProperties.getUserTopicPrefix());

        // Enables a simple in-memory broker
        registry.enableSimpleBroker(wsProperties.getPublicTopicPrefix(),
                wsProperties.getUserTopicPrefix());
        //   Use this for enabling a Full featured broker like RabbitMQ
         /*
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
        */
    }
}