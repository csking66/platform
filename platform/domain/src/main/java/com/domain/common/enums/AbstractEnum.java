package com.domain.common.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.domain.common.util.I18nUtils;

public interface AbstractEnum {

    String getSid();

    String getName();

    /**
     * 国际化内容
     *
     * @return
     */
    default String getLocaleName() {

        String code = "enum." + this.getClass().getSimpleName() + "." + getName();
        Locale locale = new Locale(Locale.ENGLISH.getLanguage());
        String value = I18nUtils.getLocaleMessage(code.toLowerCase(), locale);
        // 没有国际化内容直接返回原始name
        if (value.equals(code.toLowerCase())) {
            return getName();
        }
        return value;
    }

    default String getEnName() {

        String code = "enum." + this.getClass().getSimpleName() + "." + getName();
        Locale locale = new Locale(Locale.ENGLISH.getLanguage());
        String value = I18nUtils.getLocaleMessage(code.toLowerCase(), locale);
        // 没有国际化内容直接返回原始name
        if (value.equals(code.toLowerCase())) {
            return getName();
        }
        return value;
    }

    default Map<String, Object> getMap() {

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int id = Integer.parseInt(getSid());
            map.put("id", id);
        } catch (NumberFormatException e) {
            map.put("id", getSid());
        }
        map.put("name", this.getLocaleName());
        map.put("code", this.getName());
        map.put("enName", this.getEnName());
        map.put("lcName", this.getLocaleName());
        return map;
    }
}
