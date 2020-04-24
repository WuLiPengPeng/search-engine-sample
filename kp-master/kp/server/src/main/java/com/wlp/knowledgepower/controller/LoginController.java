package com.wlp.knowledgepower.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: wlp
 * @create: 2020-03-03 18:40
 * @description: 登录
 **/
@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/signIn")
    public String login(HttpServletRequest request , HttpServletResponse response){
        String l = request.getParameter("l");
        System.out.println(l);
        return "signIn";
    }

    @RequestMapping("/doSignIn")
    public String doSignIn(HttpServletRequest request , HttpServletResponse response){

        return "index";
    }
}
