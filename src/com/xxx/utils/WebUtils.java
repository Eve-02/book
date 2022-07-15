package com.xxx.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {

    public static <T> T copyParamToBean(T bean,Map value){
        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换为 int类型的数据
     * @param str 需要转换的字符串
     * @param defaultValue 转换不成功时的默认值
     * @return 转换后的结果
     */
    public static Integer parseInt(String str,int defaultValue){
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }

}
