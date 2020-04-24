package com.wlp.knowledgepower.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: wlp
 * @create: 2020-02-28 14:59
 * @description: Spirng Security 配置
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    VerifyCodeFilter verifyCodeFilter;
    @Autowired
    MyUserDetailsService myUserDetailsService ;

    protected void configure(AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService (myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    /**
     * 登录配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);

        //开启登录配置
        http.authorizeRequests()
                //表示访问 /hello 这个接口，需要具备 admin 这个角色
            .antMatchers("/test").hasRole("ADMIN")
                //静态资源目前不需要验证
            .antMatchers("/script/**","/css/**","/img/**","/svg/**","/i18n/signIn/**","/**.html")
            .permitAll()
                //表示剩余的其他接口，登录之后就能访问
            .anyRequest().authenticated()
            .and()
            .formLogin()
            //定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
            .loginPage("/login/signIn")
            //登录处理接口
            .loginProcessingUrl("/login/doSignIn")
            //定义登录时，用户名的 key，默认为 username
            .usernameParameter("uname")
            //定义登录时，用户密码的 key，默认为 password
            .passwordParameter("passwd")
            //登录成功的处理器
            .successHandler(new AuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("success");
                    out.flush();
                }
            })
            .failureHandler(new AuthenticationFailureHandler() {
                @Override
                public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("fail");
                    out.flush();
                }
            })
            .permitAll()//和表单登录相关的接口统统都直接通过
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(new LogoutSuccessHandler() {
                @Override
                public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("logout success");
                    out.flush();
                }
            })
            .permitAll()
            .and()
//            .httpBasic()
//            .and()
            .csrf().disable();
    }
}
