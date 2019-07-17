package com.ugrong.framework.ws.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class JsonUtil {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            DateFormat formatter = new SimpleDateFormat(PATTERN);
            mapper.setDateFormat(formatter);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
