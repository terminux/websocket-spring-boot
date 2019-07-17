package com.ugrong.framework.ws.config;

import com.ugrong.framework.ws.model.WsConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.websocket")
@Getter
@Setter
@ToString
public class WsProperties {

    /**
     * websocket的连接地址
     */
    private String endpoint = WsConstants.DEFAULT_WS_URL;

    /**
     * 客户端给服务端发消息的地址的前缀
     */
    private String messagePrefix = WsConstants.DEFAULT_WS_MESSAGE_PREFIX;

    /**
     * 客户端给服务端发消息的地址的后缀
     */
    private String messageSuffix = WsConstants.DEFAULT_WS_MESSAGE_SUFFIX;

    /**
     * 客户端接收服务端消息的主题前缀
     */
    private String publicTopicPrefix = WsConstants.DEFAULT_WS_PUBLIC_TOPIC_PREFIX;

    /**
     * 客户端接收服务端消息的主题后缀
     */
    private String publicTopicSuffix = WsConstants.DEFAULT_WS_PUBLIC_TOPIC_SUFFIX;

    /**
     * 给指定用户发送（一对一）消息的主题前缀
     */
    private String userTopicPrefix = WsConstants.DEFAULT_WS_USER_TOPIC_PREFIX;

    /**
     * 给指定用户发送（一对一）消息的主题后缀
     */
    private String userTopicSuffix = WsConstants.DEFAULT_WS_USER_TOPIC_SUFFIX;

    /**
     * your id field name of principal object
     * <p>
     * 主体对象的id字段名
     */
    private String principalIdFieldName = WsConstants.DEFAULT_WS_PRINCIPAL_ID_FIELD_NAME;
}
