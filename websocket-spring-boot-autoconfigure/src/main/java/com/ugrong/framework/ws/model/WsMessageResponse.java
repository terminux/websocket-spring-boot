package com.ugrong.framework.ws.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WsMessageResponse implements Serializable {

    private int retCode;

    private String retMsg;

    private WsMessage message;
}
