package com.wlp.knowledgepower.config.i18n;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//extends WebMvcConfigurerAdapter (过时)
//使用WebMvcConfigurerAdapter可以扩展SpringMvc的功能，包括拦截器，转换器等
//@EnableWebMvc //设置@EnableWebMvc为完全接管SpringMvc，但一般不要设置完全接管SpringMvc

@Configuration
public class CustomMvcConfig implements WebMvcConfigurer {

    //向容器中添加自己配置的国际话语言解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

}
