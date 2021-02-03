package com.kang.mall.util;

import org.springframework.beans.BeanUtils;

/**
 * @author yikang
 * ClassName: ClassUtils
 * Description: class 工具类
 * Create Date: 2021/1/20 20:33
 */
public class ClassUtils {

    public static <S, T> T copyProperties(S source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
