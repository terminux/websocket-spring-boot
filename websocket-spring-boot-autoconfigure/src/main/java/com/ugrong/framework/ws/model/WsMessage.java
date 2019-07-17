package com.ugrong.framework.ws.model;

import com.ugrong.framework.ws.api.WsMessageType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ToString
public class WsMessage implements Serializable {

    /**
     * 消息id
     */
    private String messageId;

    /**
     * 消息发送时间
     */
    private Date sendTime;

    /**
     * 消息发送者
     */
    private String sender;

    /**
     * 消息接收者
     */
    private String receiver;

    /**
     * 消息内容
     */
    private String content;

    /**
     * @see WsMessageType#getType()
     * <p>
     * 消息类型
     */
    private String msgType;

    /**
     * 事件类型
     */
    private WsEventType eventType;

    /**
     * 自定义的字段
     */
    private Map<String, Object> fields;
}
