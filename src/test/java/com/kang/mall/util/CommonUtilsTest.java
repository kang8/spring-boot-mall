package com.kang.mall.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author kang
 * ClassName: CommonUtilsTest
 * Create Date: 2021/3/15 17:18
 */
public class CommonUtilsTest {
    @Test
    public void testCheckPhoneNumber() {
        Assertions.assertTrue(CommonUtils.validatePhoneNumber("18090728264"));
        Assertions.assertTrue(CommonUtils.validatePhoneNumber("17354572975"));

        Assertions.assertFalse(CommonUtils.validatePhoneNumber("123"));
        Assertions.assertFalse(CommonUtils.validatePhoneNumber("asd"));
        Assertions.assertFalse(CommonUtils.validatePhoneNumber("173-5457-2975"));
    }
}
