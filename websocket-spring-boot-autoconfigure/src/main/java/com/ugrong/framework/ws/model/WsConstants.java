package com.ugrong.framework.ws.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WsConstants {

    public static final String DEFAULT_WS_URL = "/websocket/server";

    public static final String DEFAULT_WS_MESSAGE_PREFIX = "/app";

    public static final String DEFAULT_WS_MESSAGE_SUFFIX = "/message";

    public static final String DEFAULT_WS_PUBLIC_TOPIC_PREFIX = "/topic";

    public static final String DEFAULT_WS_PUBLIC_TOPIC_SUFFIX = "/broadcast";

    public static final String DEFAULT_WS_USER_TOPIC_PREFIX = "/user";

    public static final String DEFAULT_WS_USER_TOPIC_SUFFIX = "/queue";

    public static final String DEFAULT_WS_PRINCIPAL_ID_FIELD_NAME = "userId";
}
