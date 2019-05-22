package com.blizzard.ws;

import com.blizzard.common.interceptor.SelfHandlerInterceptor;
import com.blizzard.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-19 18:46
 */
public class SelfHandShakeInterceptor implements HandshakeInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelfHandlerInterceptor.class);


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
        String uid = req.getServletRequest().getParameter("uid");
        LOGGER.info("用户的uId为{}",uid);
        if(!StringUtil.isNumber(uid)){//用户未登陆
            return false;
        }
        HttpSession session = req.getServletRequest().getSession();
        Integer sessionUid = (Integer)session.getAttribute("uid");
        if(sessionUid == null){
            attributes.put("uid",Integer.valueOf(uid));
        }else{
            LOGGER.info("uid={}的用户已经建立连接",uid);
            return false;
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
