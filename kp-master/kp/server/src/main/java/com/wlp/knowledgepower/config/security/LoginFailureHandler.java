package com.wlp.knowledgepower.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: wlp
 * @create: 2020-02-28 15:13
 * @description: 登录失败处理器
 **/
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
//            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            httpServletResponse.setContentType("application/json;charset=utf-8");
//            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(e));
//        }else {
//        }
//        super.onAuthenticationFailure(httpServletRequest, httpServletResponse,e);
    }
}
