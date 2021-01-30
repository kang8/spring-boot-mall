package com.kang.mall.util;

import org.springframework.beans.BeanUtils;

/**
 * @author yikang
 * ClassName: ClassUtil
 * Description: class 工具类
 * Create Date: 2021/1/20 20:33
 */
public class ClassUtil {

    public static <S, T> T copyProperties(S source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
