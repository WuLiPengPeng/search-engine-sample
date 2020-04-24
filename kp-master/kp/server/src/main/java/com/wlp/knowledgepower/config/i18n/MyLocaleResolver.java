package com.wlp.knowledgepower.config.i18n;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义国际好语言解析器
 */
public class MyLocaleResolver implements LocaleResolver {
    private static final String I18N_LANGUAGE = "l";
    @Override
    public Locale resolveLocale(HttpServletRequest req) {
        String i18n_language = req.getParameter(I18N_LANGUAGE);
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(i18n_language)) {
            String[] language = i18n_language.split("_");
            locale = new Locale(language[0], language[1]);
        }
        return locale;
    }
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
