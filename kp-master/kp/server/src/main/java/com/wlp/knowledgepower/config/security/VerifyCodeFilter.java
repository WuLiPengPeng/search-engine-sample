package com.wlp.knowledgepower.config.security;

import com.wlp.knowledgepower.exception.VerifyCodeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: wlp
 * @create: 2020-02-28 15:04
 * @description: 验证码过滤器
 **/
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {
    private static final Logger log = Logger.getLogger(VerifyCodeFilter.class);
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().equals("/login")&&request.getMethod().equalsIgnoreCase("post")){
            try {
                validate(request);
            } catch (VerifyCodeException e) {
                loginFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        // 3. 校验通过，就放行
        filterChain.doFilter(request, response);
    }
    /* 验证保存在session的验证码和表单提交的验证码是否一致 */
    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        String captcha = ServletRequestUtils.getStringParameter(request, "captcha");
        String code = (String) request.getSession().getAttribute(request.getParameter("uuid"));
        log.info("获取提交的code: "+captcha);
        log.info("获取保存的code: "+code);
        if(!code.equalsIgnoreCase(captcha)){
            throw new VerifyCodeException("VerifyCode error");
        }
        request.getSession().removeAttribute(request.getParameter("uuid"));
    }
}
