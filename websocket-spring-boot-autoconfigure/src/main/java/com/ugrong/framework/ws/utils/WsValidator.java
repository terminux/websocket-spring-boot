package com.ugrong.framework.ws.utils;

import com.ugrong.framework.ws.model.WsException;
import com.ugrong.framework.ws.model.WsResponseCode;
import org.apache.commons.lang3.StringUtils;

public class WsValidator {

    public static void notBlank(String in, String errorMsg) {
        if (StringUtils.isBlank(in)) {
            throw new WsException(WsResponseCode.INVALID_PARAM, errorMsg);
        }
    }

    public static void notNull(Object in, String errorMsg) {
        if (in == null) {
            throw new WsException(WsResponseCode.INVALID_PARAM, errorMsg);
        }
    }

    public static void isTrue(boolean condition, String errorMsg) {
        if (!condition) {
            throw new WsException(WsResponseCode.INVALID_PARAM, errorMsg);
        }
    }
}
