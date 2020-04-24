package com.wlp.knowledgepower.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * @author: wlp
 * @create: 2020-02-28 15:22
 * @description: 验证码异常类
 **/
public class VerifyCodeException extends AuthenticationException {
    private static final long serialVersionUID = -336762240188509304L;
    public VerifyCodeException(String msg) {
        super(msg);
    }
}
