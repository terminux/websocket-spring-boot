package com.ugrong.framework.ws.interceptor;

import com.ugrong.framework.ws.config.WsProperties;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@AllArgsConstructor
public class DefaultWsAuthInterceptor extends WsAuthInterceptor {

    private final WsProperties wsProperties;

    @Override
    boolean doAuth(HttpServletRequest request, Map<String, Object> attr) {
        String userId = request.getParameter(this.wsProperties.getPrincipalIdFieldName());
        //校验userId(或token)的合法性 这里简单判断是否为空串
        if (StringUtils.isNotBlank(userId)) {
            //认证通过
            //把页面传递的值存储到map中传给handler处理
            attr.put(this.wsProperties.getPrincipalIdFieldName(), userId);
            return true;
        }
        return false;
    }
}
