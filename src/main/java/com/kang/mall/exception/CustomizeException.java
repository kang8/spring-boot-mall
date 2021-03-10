package com.kang.mall.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yikang
 * ClassName: CustomizeException
 * Description: 自定义异常
 * Create Date: 2021/3/9 19:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomizeException extends RuntimeException {
    public CustomizeException(String message) {
        super(message);
    }
}
