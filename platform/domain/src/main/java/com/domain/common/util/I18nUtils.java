package com.domain.common.util;

import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class I18nUtils {

    private static MessageSource messageSource;

    /**
     * @param code ：对应messages配置的key.
     * @return
     */
    public static String getMessage(String code) {

        return getMessage(code, null);
    }

    /**
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public static String getMessage(String code, Object[] args) {

        return getMessage(code, args, "");
    }

    /**
     * @param code           ：对应messages配置的key.
     * @param args           : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */
    public static String getMessage(String code, Object[] args, String defaultMessage) {

        // 这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        if (messageSource == null) {
            messageSource = SpringUtils.getBean(MessageSource.class);
        }
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    public static String getLocaleMessage(String code, Locale locale) {

        // 这里使用比较方便的方法，不依赖request.
        if (messageSource == null) {
            messageSource = SpringUtils.getBean(MessageSource.class);
        }
        return messageSource.getMessage(code, null, "", locale);
    }

    public static void setNameValues(Map<String, String> nameValues, String name) {

        if (nameValues != null) {
            nameValues.put("", name);
        }

    }

}